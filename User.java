package com.FinalProject1;


//abstract class (10 points)
//also is the super class a.k.a parent class
abstract class User {

        //attributes
        private String userName;

        private String password;

       //Encapsulation (10 points)
       
        //getter
        public String getusername() {
            return userName;
        }

        //setter
        public void setusernmae(String username) {
            this.userName = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        //abstract method for create
        public abstract String create();
    }

//sub class for Admin users
//Inheritance (10 points)
    class Administrator extends User {
        //attributes
        private String Email;
        private String FirstName;
        private String LastName;



        private String[] ApplicationName=new String[3];

        public String[] getApplicationName() {

            return ApplicationName;
        }

        public String getApplicationNameToString() {

            String[] theApplications = new String[3];
            theApplications=getApplicationName();
            String strApplications="";
            for (int i=0;i<3;i++)
            {
                strApplications=strApplications + theApplications[i] + " - ";
            }
            return strApplications.trim();
        }

        public void setApplicationName(String[] applicationName) {
            ApplicationName = applicationName;
        }

        //getter-email
        public String getEmail() {
            return Email;
        }

        //setter-email
        public void setEmail(String Email) {
            this.Email = Email;
        }

        //getter-firstname
        public String getFirstName() {
            return FirstName;
        }

        //setter-firstname
        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        //getter-lastname
        public String getLastName() {
            return LastName;
        }

        //setter-lastname
        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        //parametrized constructor for Administrator
        public Administrator(String username, String password, String email, String firstName, String lastName){
            super.setusernmae(username);
            super.setPassword(password);
            this.setEmail(email);
            this.setFirstName(firstName);
            this.setLastName(lastName);
            String[] applications=new String[3];
            applications[0]="Payroll";
            applications[1]="Accounts Receivable";
            applications[2]="Accounts Payable";
            this.setApplicationName(applications);

        }
//Overriding (10 points)
        @Override
        public String create(){
            String line= super.getusername()  + "," + super.getPassword() + "," +  "Administrator" + "," +  getApplicationNameToString();
            File storage= new Storage();
            storage.save(line);
            return "The user name was created in the file ";
        }


    }

//subclass for Standard users
    class Standard extends User {
        //attributes
        private String Email;
        private String FirstName;
        private String LastName;
        private String ApplicationName;

        //getter-email
        public String getEmail() {
            return Email;
        }

        //setter-email
        public void setEmail(String Email) {
            this.Email = Email;
        }

        //getter-firstname
        public String getFirstName() {
            return FirstName;
        }

        //setter-firstname
        public void setFirstName(String FirstName) {
            this.FirstName = FirstName;
        }

        //getter-lastname
        public String getLastName() {
            return LastName;
        }

        //setter-lastname
        public void setLastName(String LastName) {
            this.LastName = LastName;
        }

        public String getApplicationName() {
            return ApplicationName;
        }

        public void setApplicationName(String applicationName) {
            ApplicationName = applicationName;
        }

        //parametrized constructor for Administrator
        public Standard(String username, String password, String email, String firstName, String lastName, int application){
            super.setusernmae(username);
            super.setPassword(password);
            this.setEmail(email);
            this.setFirstName(firstName);
            this.setLastName(lastName);

            if (application==1) setApplicationName("Payroll");
            if (application==2) setApplicationName("Accounts Receivable");
            if (application==3) setApplicationName("Accounts Payable");
        }

        @Override
        public String create(){
            String line= super.getusername()  + "," + super.getPassword() + "," +  "Standard" + "," + getApplicationName();
            File storage= new Storage();
            storage.save(line);
            return "The user name was created in the file ";
        }
    }
