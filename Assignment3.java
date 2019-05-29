//	Diego Veras, CGS3416, 11/01/18

import java.util.Scanner;

public class Assignment3 {

	public static void main(String[] args) {

	        int n=3;

	        int[][] firstMatrix= new int[n][n];			//creating first matrix
	        int[][] secondMatrix= new int[n][n];		//creating second matrix
	        int[][] outMatrix= new int[n][n];			//creating results matrix
	      
	        System.out.println("Enter 1st 3x3 matrix:");
	        firstMatrix=readMatrix(n,n);
	       
	        System.out.println("Enter 2nd 3x3 matrix:");
	        secondMatrix=readMatrix(n,n);
	       
	        outMatrix=multiplication(firstMatrix,secondMatrix);
	        printMatrix(outMatrix);
	        pal();

	    }
	
	public static int[][] readMatrix(int rows, int columns) {		//read matrix inputs
	     
		int[][] matrix= new int[rows][columns];

	        for (int i=0; i < rows ; i++)
	            for(int j=0;j< columns; j++)
	            {
	                Scanner numberin = new Scanner(System.in);
	                matrix[i][j]=numberin.nextInt();
	            }
	        
	        return matrix;
	    }

	public static void printMatrix(int[][] matrix) {			//print out response matrix

	        for (int i=0; i < matrix.length ; i++) {
	            for (int j = 0; j < matrix.length;j++)
	            {
	                System.out.print(matrix[i][j]  + "  ");

	            }
	            System.out.println();
	        }
	    }


	public static int[][] multiplication(int[][] firstmatrix, int[][] secondmatrix)		//calculate matrix results by position
	    {
	        int[][] outmatrix=new int[firstmatrix.length][firstmatrix.length];
	     
	        for (int i=0; i <firstmatrix.length ; i++) {
	            for (int j = 0; j < firstmatrix.length; j++) {
	                for (int k = 0; k < secondmatrix.length; k++){
	                        outmatrix[i][j] += firstmatrix[i][k] * secondmatrix[k][j];
	                    }
	            }
	        }
	        return outmatrix;
	    
	        
	    }
	        
	
public static void pal() {
	boolean palindrome = false;
	System.out.println("Enter the string: ");
	Scanner input = new Scanner (System.in);
	String text = input.nextLine();
	int n = text.trim().length();
	
	for(int i =0; i < n; i++) {
		if(text.charAt(i)== text.charAt(n-1-i)) {			//check if corresponding letters match
			palindrome = true;
		}
		else 
			break;
		}
	if (palindrome ==true) {
		System.out.println("Yes, " + text + " is a palindrome");
	}
	else {
		System.out.println("No, " + text + "is not a palindrome");
	}
}
	}

public class Assigment3 {

	public static void main(String[] args) {

	        int n=3;

	        int[][] firstMatrix= new int[n][n];			//creating first matrix
	        int[][] secondMatrix= new int[n][n];		//creating second matrix
	        int[][] outMatrix= new int[n][n];			//creating results matrix
	      
	        System.out.println("Enter 1st 3x3 matrix:");
	        firstMatrix=readMatrix(n,n);
	       
	        System.out.println("Enter 2nd 3x3 matrix:");
	        secondMatrix=readMatrix(n,n);
	       
	        outMatrix=multiplication(firstMatrix,secondMatrix);
	        printMatrix(outMatrix);
	        pal();

	    }
	
	public static int[][] readMatrix(int rows, int columns) {		//read matrix inputs
	     
		int[][] matrix= new int[rows][columns];

	        for (int i=0; i < rows ; i++)
	            for(int j=0;j< columns; j++)
	            {
	                Scanner numberin = new Scanner(System.in);
	                matrix[i][j]=numberin.nextInt();
	            }
	        
	        return matrix;
	    }

	public static void printMatrix(int[][] matrix) {			//print out response matrix

	        for (int i=0; i < matrix.length ; i++) {
	            for (int j = 0; j < matrix.length;j++)
	            {
	                System.out.print(matrix[i][j]  + "  ");

	            }
	            System.out.println();
	        }
	    }


	public static int[][] multiplication(int[][] firstmatrix, int[][] secondmatrix)		//calculate matrix results by position
	    {
	        int[][] outmatrix=new int[firstmatrix.length][firstmatrix.length];
	     
	        for (int i=0; i <firstmatrix.length ; i++) {
	            for (int j = 0; j < firstmatrix.length; j++) {
	                for (int k = 0; k < secondmatrix.length; k++){
	                        outmatrix[i][j] += firstmatrix[i][k] * secondmatrix[k][j];
	                    }
	            }
	        }
	        return outmatrix;
	    
	        
	    }
	        
	
public static void pal() {
	boolean palindrome = false;
	System.out.println("Enter the string: ");
	Scanner input = new Scanner (System.in);
	String text = input.nextLine();
	int n = text.trim().length();
	
	for(int i =0; i < n; i++) {
		if(text.charAt(i)== text.charAt(n-1-i)) {			//check if corresponding letters match
			palindrome = true;
		}
		else 
			break;
		}
	if (palindrome ==true) {
		System.out.println("Yes, " + text + " is a palindrome");
	}
	else {
		System.out.println("No, " + text + "is not a palindrome");
	}
}
	}
	
			

	    
	    



