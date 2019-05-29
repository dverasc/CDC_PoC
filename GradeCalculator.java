//Diego Veras, September 16, 2019 , CGS3416-0001

import java.util.Scanner;

public class gradecalc {
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to the 2018 Java Gradebook"); 
		System.out.println("Please enter the grades for the following items");
		
		Scanner s = new Scanner(System.in);
		
		int mark[] = new int[4];
		 
		int i;
		float hw=0, hwavg;
		     
	//this is the hw calculation
		for(i=0; i < 4; i++) {
			System.out.println("Homework " + Integer.toString(i+1) + ":");
			mark[i] = s.nextInt();
			hw = hw + mark[i];
			
		}
		
		int j;
		float test=0, testavg;
		//this is the test caulculation	
		
		for(j=0; j < 2; j++) {
			System.out.println("Test " + Integer.toString(j+1) + ":");
			mark[j] = s.nextInt();
			test = test + mark[j];
		}
			
		hwavg = (float) ((hw/4)*.4) ;
		//System.out.println("homework avg: " +avge);//test1starray
		
		testavg = (float) ((test/2)*.6);
		
		float avg = hwavg + testavg;
		//System.out.println("homework avg: " +notavg);//test2ndarray
		
		
		System.out.println("Your final grade in the course is: " +avg);
		System.out.print("Your Letter Grade is ");
		if(avg >= 92) 
		{
			System.out.println("A");
		}
		else if(avg >= 90 && avg <= 91.99)
        {
            System.out.println("A-");
        }
        else if(avg >= 88 && avg <=89.99)
        {
            System.out.println("B+");
        }
        else if(avg >= 82 && avg <= 87.99)
        {
            System.out.println("B");
        }
        else if(avg >= 80 && avg <= 81.99)
        {
            System.out.println("B-");
        }
        else if(avg >= 78 && avg <=79.99)
        {
            System.out.println("C+");
        }
        else if(avg >= 72 && avg <=77.99)
        {
            System.out.println("C");
        }
        else if(avg >= 69 && avg <=71.99)
        {
            System.out.println("C-");
        }
        else if(avg >= 62 && avg <=68.99)
        {
            System.out.println("D");
        }
        else if(avg >= 60 && avg <=61.99)
        {
            System.out.println("D-");
        }
        else if(avg >= 0 && avg <=59.99)
        {
            System.out.println("F");
        }
		
		System.out.println("Thanks for using the grade calculator, goodbye!"); 
	
	}
	
	
}
