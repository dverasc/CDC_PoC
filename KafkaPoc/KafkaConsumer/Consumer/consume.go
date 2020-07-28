package main

import (
	"context"
	"database/sql"
	"encoding/json"
	"fmt"
	"io/ioutil"
	"log"
	"os"

	// "github.com/Shopify/sarama"
	// "github.com/wvanbergen/kafka/consumergroup"

	"github.com/Shopify/sarama"
	"github.com/wvanbergen/kafka/consumergroup"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"

	"net/url"
	"time"

	"gopkg.in/yaml.v2"
)

var client *mongo.Client
var cg *consumergroup.ConsumerGroup

//test change again
//ErrorInfo used for error response on failure
type ErrorInfo struct {
	Error            string `json:"Error"`
	AlertToDisplay   string `json:"AlertToDisplay"`
	ServerName       string `json:"ServerName"`
	HTMLResponseCode int    `json:"HTMLResponseCode"`
	GUISessionID     string `json:"GUISessionID"`
	ErrorValidation  string `json:"ErrorValidation"`
}

//MsgStruct to unmarshal bytes into
type MsgStruct struct {
	StartLsn   string        `bson:"start_lsn" json:"start_lsn"`
	Operation  string        `bson:"operation" json:"operation"`
	UpdateMask sql.NullInt64 `bson:"updatemask" json:"updatemask"`
	ID         string        `bson:"id"`
	Name       string        `bson:"Name"`
}
type DatabaseConfigType struct {
	Databasename         string
	Hosts                []string
	Username             string
	Password             string
	CollectionName       string
	SecurityUsername     string
	SecurityPassword     string
	SecurityDatabasename string
}

//User1 bad naming practice but this is the struct to append the unmarshal too
type User1 struct //user query
{
	Data MsgStruct
}

//kafka connection is constant
const (
	zookeeperConn = "10.0.0.38:22181"
	cgroup        = "zgroup"
	topic         = "email_topic"
)

func main() {
	// setup sarama log to stdout
	var err error
	err = OpenDB()
	if err != nil {
		fmt.Println("Error with opening the db: ", err.Error())
		os.Exit(1)
	}
	sarama.Logger = log.New(os.Stdout, "", log.Ltime)
	// init consumer
	err = initConsumer()
	if err != nil {
		fmt.Println("Error consumer goup: ", err.Error())
		os.Exit(1)
	}

	for {
		consume1()
	}

}

func initConsumer() error {

	//   consumer config
	config := consumergroup.NewConfig()
	config.Offsets.Initial = sarama.OffsetOldest
	config.Offsets.ProcessingTimeout = 10 * time.Second
	var err error
	//       // join to consumer group
	cg, err = consumergroup.JoinConsumerGroup(cgroup, []string{topic}, []string{zookeeperConn}, config)
	if err != nil {
		return nil
	}
	return err

}

//CheckError is a comment
func CheckError(e error) {
	if e != nil {
		fmt.Println(e)
	}
}

func consume1() {
	go func() {
		for {
			select {
			case msg := <-cg.Messages():
				//   messages coming through chanel
				//   only take messages from subscribed topic
				if msg.Topic != topic {
					continue
				}
				// var msg1 sarama.ConsumerMessage
				//stringmsg := string(msg.Value)
				// consume(stringmsg)
				saveMsgToMongoNew(msg)
				// if err != nil {
				// 	fmt.Println("Error at saving: ", err.Error())
				// } else {
				// 	//commit to zookeeper that message is read
				// 	// this prevent read message multiple times after restart

				// }
			}
		}
	}()
}

// func consume(stringmsg string) {

// 	//fmt.Println("Topic: ", msg.Topic)
// 	//fmt.Println("Value: ", msg.Value)
// 	println("here's a print stm to review: ", stringmsg)
// 	saveMsgToMongoNew(stringmsg)

// }

//DatabaseConfigType is struct for connecting to MongoDB

//
func getDefaultValues() (*DatabaseConfigType, error) {
	var dbConfiguration DatabaseConfigType
	dataConfiguration, err := ioutil.ReadFile("diagrams.yml")

	if err != nil {
		return nil, err
	} else {
		err := yaml.Unmarshal(dataConfiguration, &dbConfiguration)
		fmt.Println("adasdadasdasd******", dbConfiguration)
		if err != nil {
			return nil, err
		} else {
			return &dbConfiguration, nil
		}
	}
}

//OpenDB shut up
func OpenDB() error {
	dbConfiguration, err := getDefaultValues()
	fmt.Println("dbConfiguration*************==>", dbConfiguration)
	if err != nil {
		//
		fmt.Println("dbConfiguration==>: ", err.Error())
		return err
		//
	}
	//
	var theHost string
	for i, Host := range dbConfiguration.Hosts {
		//
		if i > 0 {
			theHost = theHost + ","
		}
		theHost = theHost + Host
		//
	}
	//
	ctx := context.Background()
	ctx, cancel := context.WithTimeout(context.Background(), 60*time.Second)
	defer cancel()

	////
	uri := fmt.Sprintf(`mongodb://%s`, theHost)
	fmt.Println("Uri===>", uri)

	if dbConfiguration.Username != "" && dbConfiguration.Password != "" {
		uri = fmt.Sprintf(`mongodb://%s:%s@%s/%s?authMechanism=SCRAM-SHA-1`, dbConfiguration.Username, url.QueryEscape(dbConfiguration.Password), theHost, dbConfiguration.Databasename)
	}

	client, err = mongo.NewClient(options.Client().ApplyURI(uri))
	if err != nil {
		//
		fmt.Println("Error Database Connect==>", err.Error())

		return err
		//
	}
	//
	err = client.Connect(ctx)
	if err != nil {
		//
		fmt.Println("Error Database connect Client==>", err.Error())
		return err
		//
	}
	//

	err = client.Ping(context.TODO(), nil)

	if err != nil {
		return err
	} else {
		return nil
	}

}
func saveMsgToMongoNew(msg *sarama.ConsumerMessage) error {

	//
	msgValue := string(msg.Value)
	// get collection as ref
	collection := client.Database("consumertest").Collection("cdc_data")
	// defer client1.Disconnect(context.TODO())
	var dataStruct MsgStruct
	var dat User1

	println("the struct ", msgValue)
	msgValueTr := []byte(msgValue)
	err2 := json.Unmarshal((msgValueTr), &dataStruct)
	// ([]byte(msgValue)
	dat.Data = dataStruct
	//println("the struct s", dat)
	fmt.Println("Data : ", dat)
	if err2 != nil {
		println("Error at Second If")
		CheckError(err2)
		return err2
	} else {
		_, errMongo := collection.InsertOne(context.TODO(), dat)
		if errMongo == nil {
			println("done...")
			err := cg.CommitUpto(msg)
			if err != nil {
				fmt.Println("Error commit zookeeper: ", err.Error())
			}
			return nil
		} else {
			//println("Error found:  ", errMongo)
			fmt.Println("Error found : ", errMongo)
			return errMongo
		}

	}
}
