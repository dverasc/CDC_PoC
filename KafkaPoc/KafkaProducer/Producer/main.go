package main

import (
	"database/sql"
	"encoding/json"
	"fmt"
	"log"
	"os"

	"github.com/Shopify/sarama"

	_ "github.com/denisenkom/go-mssqldb"
)

//ErrorInfo is to report issues as json
type ErrorInfo struct {
	Error             string `json:"Error"`
	AlertToDisplay    string `json:"AlertToDisplay"`
	ServerName        string `json:"ServerName"`
	HtmlResponseCode  int    `json:"HtmlResponseCode"`
	NewId             string `json:"NewId"`
	NewInvoiceId      string `json:"NewInvoiceId"`
	CodingError       string `json:"CodingError"`
	SequenceInvoiceId string `json:"SequenceInvoiceId"`
	GUISessionId      string `json:"GUISessionId"`
	ErrorValidation   string `json:"ErrorValidation"`
}

//User query
type User struct //user query
{
	StartLsn []uint8 `json:"start_lsn"`

	Operation  []uint8       `json:"operation"`
	UpdateMask sql.NullInt64 `json:"updatemask"`
	ID         string        `json:"ID"`
	Name       string        `json:"Name"`
}

//here
//ManageError is for errors
func ManageError(errorStr string) ErrorInfo {
	//
	var error ErrorInfo
	error.Error = errorStr
	error.ServerName, _ = os.Hostname()
	error.HtmlResponseCode = 200
	error.NewId = ""
	error.ErrorValidation = ""
	//
	return error
	//
}

const (
	kafkaConn = "10.0.0.38:19092"
	topic     = "email_topic"
)

func main() {
	// create producer
	producer, err := initProducer()
	if err != nil {
		fmt.Println("Error producer: ", err.Error())
		os.Exit(1)
	}
	for {
		producercreate(producer)

	}

}

//hello
func producercreate(producer sarama.SyncProducer) {
	db, err := sql.Open("mssql", "server=localhost;user id=sa;password=Fsudvc@41197;database=aspnetproviderdb")
	// Docker IP server=172.17.0.2 or 3
	if err != nil {
		log.Fatal(err)
	} else {
		println("Connection succesful")
	}
	defer db.Close()
	err = db.Ping()
	if err != nil {
		log.Fatal(err)
	}
	row, err := db.Prepare("DECLARE @from_lsn binary(10), @to_lsn binary(10), @row_filter_option nvarchar(30);SET @from_lsn = sys.fn_cdc_get_min_lsn('AspNetRoles');SET @to_lsn = sys.fn_cdc_get_max_lsn();SELECT * FROM [cdc].[fn_cdc_get_net_changes_AspnetRoles](@from_lsn, @to_lsn, N'all');")
	rows, err := row.Query()
	if err != nil {
		log.Fatal("Error with executing the query: ", err.Error())
	} else {
		// UserDefinitionResults := make([]User, 0) //this is arrray declaration, it is dynamic because we don't know the exact array dimensions
		for rows.Next() {
			UserDefinitionResult1 := User{} //this is defining struct for ONE instance of the row
			err := rows.Scan(&UserDefinitionResult1.StartLsn, &UserDefinitionResult1.Operation, &UserDefinitionResult1.UpdateMask, &UserDefinitionResult1.ID, &UserDefinitionResult1.Name)
			if err != nil {
				log.Fatal("The issue with executing the query is: ", err.Error())
			} else {
				// UserDefinitionResults = append(UserDefinitionResults, UserDefinitionResult1) //here we append the results from each instance into a combined array
				defer rows.Close()
				// print(UserDefinitionResults)
				msg, errmsg := json.Marshal(UserDefinitionResult1)
				if errmsg == nil {
					fmt.Println(msg)
				}
				if errmsg != nil {
					fmt.Println("The error is ", err)
				}
				// publish without goroutine
				err := publish(msg, producer)
				if err == nil {
					deleterow, err := db.Prepare("Delete FROM [cdc].[AspNetRoles_CT] WHERE [__$start_lsn] = ?")
					if err == nil {
						deleterow.Exec(UserDefinitionResult1.StartLsn)
						fmt.Println("Ran fine")
					}
				}
				if err != nil {
					fmt.Println("The error is ", err)
				}
			}
		}

	}
}

func initProducer() (sarama.SyncProducer, error) {
	// setup sarama log to stdout
	sarama.Logger = log.New(os.Stdout, "", log.Ltime)

	// producer config
	config := sarama.NewConfig()
	config.Producer.Retry.Max = 5
	config.Producer.RequiredAcks = sarama.WaitForAll
	config.Producer.Return.Successes = true

	// async producer
	//prd, err := sarama.NewAsyncProducer([]string{kafkaConn}, config)

	// sync producer
	prd, err := sarama.NewSyncProducer([]string{kafkaConn}, config)

	return prd, err
}

func publish(message []byte, producer sarama.SyncProducer) error {
	// publish sync
	msg := &sarama.ProducerMessage{
		Topic: topic,
		Value: sarama.StringEncoder(message),
	}
	p, o, err := producer.SendMessage(msg)
	if err != nil {
		fmt.Println("Error publish: ", err.Error())
	}

	// publish async
	//producer.Input() <- &sarama.ProducerMessage{

	fmt.Println("Partition: ", p)
	fmt.Println("Offset: ", o)
	return err
}
