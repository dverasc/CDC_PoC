//Diego Veras, dv15e

import java.util.Scanner;

public class Evaluate {

	public static void main(String[] args) {

		
		Scanner sentence = new Scanner (System.in);		//user input for sentence analysis
		System.out.println("Please enter a sentence");
		String input = sentence.nextLine();
		evaluate(input);
		evaluate();
		
	}
	
	//methods for arithmetic operations
	public static int add(int a, int b)
	{
		return a + b;
	}
	public static int sub(int a, int b)
	{
		return a - b;
	}
	public static int mult(int a, int b)
	{
		return a * b;
	}
	public static int div(int a, int b)
	{
		return a / b;
	}
	public static int perc(int a, int b)
	{
		return (a * b) / 100;
	}
	
	//evaluate method for arithmetic
	 
	public static void evaluate(){
       
		System.out.println("Enter the expression");
        int result=0;
        String input;
        String operation="";
        int value=0;
        Scanner scvalue = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);
        
       
        do
        {

            value = scvalue.nextInt();
            if (operation.equals("+"))
            {
                result=add(result, value);

            }
            else if (operation.equals("-")) {
                result=sub(result, value);

            }
            else if (operation.equals("*")) {
                result=mult(result, value);
            }

            else if (operation.equals("/")) {
                result=div(result, value);
            }

            else if (operation.equals("%")) {
            	
                result=perc(result, value);
            }
            else
                result=value;

            input = scanner.nextLine();
            if (! input.equals("?") ) {
                operation=input;
            }

        } while(!input.equals("?"));

        System.out.println("The result is " + result);
            
    }
	
	//evaluate method for word count
	public static void evaluate(String sentence)
   {   
	   int counter=0;
	   String worlds[]=sentence.split(" ");	 
	   for (int i=0; i<worlds.length;i++)
	   {
		   if (worlds[i].trim().length()!=0)
		   {
			   counter++;
		   }
			   
	   }
	   System.out.println("There are " + counter + " words in the sentence");
	   }
   	
}
