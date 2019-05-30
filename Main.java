//java package (10 points)
package com.FinalProject1;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

	        System.out.println("Welcome to the User Dashboard" + "\n" + "Please select an option from the menu below");
	        Scanner number = new Scanner(System.in);
	        int choice;
	        do {
	            System.out.println("1.- Create User ");
	            System.out.println("2.- Login");
	            System.out.println("3.- Exit");
	            choice = number.nextInt();
	            if (choice == 1) {
	                createUsers();
	            } else if (choice == 2) {

	                login();
	            }


	        } while (choice != 3);
	    }


	        public static void login(){
	            Scanner text = new Scanner(System.in);

	            System.out.println("User Name: ");
	            String username = text.nextLine();
	            System.out.println("Password: ");
	            String password = text.nextLine();
	            Security security= new Security();

	            security.login(username, password);

	        }

	        public static void createUsers () {


	            System.out.println("To create an account, please enter your desired username, password, and level of clearance");
	            Scanner text = new Scanner(System.in);
	            Scanner number = new Scanner(System.in);
	            //
	            System.out.println("User Name:");
	            String username = text.nextLine();
	            //
	            System.out.println("Password:");
	            String password = text.nextLine();

	            System.out.println("Clearance Levels are as follows:" + "\n" + "Administrator: 1" + "\n" + "Standard: 2");

	            System.out.println("Level of clearance:");
	            int lev = number.nextInt();
	            //
	           // User user;
	            //
	            if (lev == 1) {

	                System.out.println("Email Account:");
	                String email = text.nextLine();
	                //
	                System.out.println("First Name:");
	                String firstname = text.nextLine();
	                //
	                System.out.println("Last Name:");
	                String lastname = text.nextLine();
	                //
	                User administrator = new Administrator(username, password, email, firstname, lastname);
	                administrator.create();

	            }
	            if (lev == 2) {
	                System.out.println("Email Account:");
	                String email = text.nextLine();
	                //
	                System.out.println("First Name:");
	                String firstname = text.nextLine();
	                //
	                System.out.println("Last Name:");
	                String lastname = text.nextLine();
	                //
	                System.out.println("Select an application");
	                System.out.println("1.- Payroll, 2.- Accounts Receivable, 3.- Accounts Payable");
	                int application = number.nextInt();
	                User standard = new Standard(username, password, email, firstname, lastname, application);
	                standard.create();
	            }
	        }

	}



