package com.userproject;

import java.io.*;


public class Storage implements File {
    String fileName="usersfile.txt";
    public void save(String Line) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {

            bw.write(Line);
            bw.newLine();


        } catch (IOException e) {

            e.printStackTrace();
            System.out.println("Error found: " + e.toString());

        }

    }


    public String retrieve(String username){
        try
            {
                BufferedReader br = new BufferedReader(new FileReader(fileName));

                    String sCurrentLine;
                    while ((sCurrentLine = br.readLine()) != null) {
                        String[] data = sCurrentLine.split(",", 4);
                        if (data[0].trim().equals(username.trim()) ){
                            return sCurrentLine;
                        }

                    }

            }
            catch (IOException e)
            {

                e.printStackTrace();
                System.out.println("Error found opening the input file: " + e.toString());

            }
            return "";

        }


}
