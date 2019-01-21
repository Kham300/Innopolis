package main.java;

import java.io.Console;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static main.java.ConsoleParser.*;

public class Main {

    public static void main(String[] args) {

        String userInput;

        //declate a scanner object to read the command line input by user
        Scanner sn = new Scanner(System.in);

        //loop the utility in loop until the user makes the choice to exit
        while (true) {
            //Print the options for the user to choose from
            System.out.println("*****Available Options*****");
            System.out.println("*. Enter <parse> for parsing");
            System.out.println("*. Enter <serialize> for serialize");
            System.out.println("*. Enter <deserialize> to deserialize");
            // Prompt the use to make a choice
            System.out.println("Enter your choice:");

            //Capture the user input in scanner object and store it in a pre decalred variable
            userInput = sn.next();

            //Check the user input
            switch(userInput){
                case "parse":
                    try {
                        parse();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;
                case "serialize":
                    serialize();
                    break;
                case "deserialize":
                    deserialize();
                    break;
            }
        }
    }
}

