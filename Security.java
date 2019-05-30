package com.userproject;

public class Security {

    public void login(String userName, String pasword) {
        File storage = new Storage();
        String linetext = storage.retrieve(userName);
        String[] data = linetext.split(",", 4);
        String savedPassword = data[1];

        if (savedPassword.trim().equals(pasword.trim() ) )
        {
            System.out.println("User logged");
            System.out.println("Level: " + data[2]);
            System.out.println("Applications " + data[3]);
            System.out.println("==============================");
        }
        else{
            System.out.println("Error, invalid user name or password");
            System.out.println("==============================");
        }


    }


}
