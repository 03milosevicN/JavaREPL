package com.example;

//? SUBPROGRAM FOR TESTING FUNCTIONALITES

import java.io.File;
import java.io.IOException;
import java.lang.Exception;
import java.util.Scanner;
import java.nio.file.*;

public class PathTest {

// * WORKING WITH `java.io.File` CLASS:

    public static void DIR_FUNCTION() {
        


        try {
        
            Scanner scanner = new Scanner(System.in);
            String insertFileName = scanner.nextLine();
            scanner.close();

            File DIR = new File("dir.txt");

            if(!DIR.getName().equals(insertFileName)) {
                System.out.println("ERROR: Incorrect file name.");
            } else {
                if(!DIR.exists()) {
                    throw new Exception("File does not exist.");
                }
                else {

                }
            }
            System.out.println("Info on directory `DIR`: " + DIR.exists());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

// * PREFERABLE METHODS HERE (FROM `java.nio.file.*`):

    public static void PATH_FUNCTION() {

        

        // System.out.println("File name: " + dir.getFileName());
        // System.out.println("Parent folder name: " + dir.getParent());
        // System.out.println("Root: " + dir.getRoot());
        // System.out.println("Absolute path: " + dir.toAbsolutePath());
        // System.out.println("Normalized path: " + dir.normalize());

        // File DIR = PATH.toFile();
        // DIR.getParent();
        
        // Path PATH1 = Paths.get("usr/local/bin/drivers/driver.txt");
        // PATH1.normalize();
        // System.out.println(PATH1);

        try {

            Path PATH = Paths.get("src/main/java/Executable.java");

            String PATH_NAME = PATH.getFileName().toString();

            //? STRING MANIPULATION METHODS:
            //int EXTENSION = PATH_NAME.lastIndexOf('.');
            //String PATH_NAME_NO_EXTENSION = PATH_NAME.substring(0, EXTENSION);
            //System.out.println(PATH_NAME_NO_EXTENSION);

            Scanner scanner = new Scanner(System.in);
            String insertFileName = scanner.nextLine();
            scanner.close();

            if (!Files.exists(PATH)) {
                Files.createFile(PATH);
                System.out.println("File created at path: " + PATH.normalize());
            }
            else {
                if (!PATH_NAME.equals(insertFileName)) {
                    throw new Exception("ERROR: Invalid file name.");
                }
                else {
                    System.out.println(PATH.normalize());
                }
            }

        } catch (Exception ex) {
            System.out.println("ERROR: Exception caught.");
            ex.printStackTrace();
        }    

    }

    public static void FILE_IO_FUNCTION() {

        Path FILE = Paths.get("javarepl/src/main/java/com/example/Executable.java");

        

        try {

            String FILE_CONTENT = "//Hello, World!";
            Files.write(FILE, FILE_CONTENT.getBytes());
            System.out.println("Content written to file.");

            byte[] CONTENT = Files.readAllBytes(FILE);
            System.out.println("File content: " + new String(CONTENT));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    
    public static void PATH_IMPLEMENT() {
        String[] availableCommands = {"echo", "exit", "typeof"};

        for (String command : availableCommands) {
            try {
                // Create a Path object for a placeholder file
                Path dir = Paths.get(command + ".txt");  // Append ".txt" to create a valid file name

                // Create a placeholder file if it doesn't exist
                if (!Files.exists(dir)) {
                    Files.createFile(dir);
                }

                // Create the target directory where the command files will be moved
                Path newDir = Paths.get("NEW_DIR");

                // Ensure the target directory exists
                Files.createDirectories(newDir);

                // Define the target path within the new directory
                Path targetPath = newDir.resolve(dir.getFileName());

                // Move the file to the new directory
                Files.move(dir, targetPath, StandardCopyOption.REPLACE_EXISTING);

                System.out.println("Moved " + command + " to " + targetPath.toAbsolutePath());
            } catch (IOException e) {
                System.err.println("Failed to move " + command + ": " + e.getMessage());
            }
        }
    }
    

    public static void PATH_COMMAND() {

        String[] COMMANDS = {"echo", "exit", "typeof"};

        for (String COMMAND : COMMANDS) {

            try {
                Path DIR = Paths.get(COMMAND + ".txt");
                System.out.println(DIR);

                Path COMMANDS_DIR = Paths.get("COMMANDS");

                Path TARGET = COMMANDS_DIR.resolve(DIR.getFileName());
                
                Files.move(DIR, TARGET, StandardCopyOption.REPLACE_EXISTING);
                
                if (!Files.exists(DIR)) {
                    if (!Files.exists(TARGET)) {
                        throw new IOException("ERROR: File not at targeted path.");
                    }
                    Files.createFile(DIR);
                }

                System.out.println("Moved " + COMMAND + " to: " + TARGET.toAbsolutePath());



            } catch (IOException e) {
                System.err.println("ERROR: Failed to move file " + COMMAND + ": " + e.getMessage());
            }
        }

    }

    public static void main(String[] args) {

        PATH_COMMAND();

    }


}
