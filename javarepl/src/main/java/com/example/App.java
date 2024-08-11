package com.example;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    
    public static void main(String[] args) throws Exception {
        System.out.print("$ ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] availableCommands = {"echo", "exit", "typeof"};
        String substring;

        

        //? main loop:
        while (!input.isEmpty()) {

            //? "exit" || "exit 0": exits shell
            if (input.equals("exit 0") || input.equals("exit")) {
                break;
            }


            //? "echo": print statement
            if (input.startsWith("echo")) {
                System.out.println(input.substring(5).trim());
            }


            //? "typeof": is command valid
            if (input.startsWith("typeof ")) {
                substring = input.substring(7).trim(); 

                if (Arrays.asList(availableCommands).contains(substring)) {
                    System.out.println(substring + ": is a shell builtin");
                } else {
                    System.out.println(substring + ": not found");
                }
            }

            if(input.startsWith("PATHS")) {


                for (String command : availableCommands) {

                    Path commands = Paths.get(command);
                    System.out.println("Path to " + command + ": " + commands.toAbsolutePath());

                }

            }

            System.out.print("$ ");
            input = scanner.nextLine(); 
            
        }
        scanner.close();
    }
}
