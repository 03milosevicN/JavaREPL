package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;

public class App {

    
    public static void main(String[] args) throws Exception {
        System.out.print("java-repl/$ ");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        String[] availableCommands = {
            "echo", 
            "exit",
            "exit 0", 
            "typeof", 
            "paths",
            "/ "
        };

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

            //? "paths": prints out all valid commands as "file locations" from where the commands execute, per se
            if(input.startsWith("paths")) {

                for (String command : availableCommands) {

                    Path commands = Paths.get(command);
                    System.out.println("Path to " + command + ": " + commands.toAbsolutePath());

                }

            }


            //? "/ ": executes shell script
            if (input.startsWith("/")) {

                String systemPath = "/c/Users/nikol/vscodeProjects/java-repl/javarepl/src/main/java/com/example/exec.sh";

                ProcessBuilder pb = new ProcessBuilder("bash", systemPath);

                try {
                    Process p = pb.start();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));

                    String line;

                    while ((line = reader.readLine()) != null) {
                        System.out.println(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            


            System.out.print("java-repl/$ ");
            input = scanner.nextLine(); 
            
        }
        scanner.close();
    }
}
