//TODO: extend typeof function with PATH functionality (see: https://app.codecrafters.io/courses/shell/stages/mg5)
//TODO: test searching for PATH functionality via the java.io.File lib

//? SUBPROGRAM FOR TESTING ABOVE-MENTONED FUNCTIONALITY

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

            Path PATH = Paths.get("src/main/java/mainFile.java");

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

        Path FILE = Paths.get("src/main/java/Executable.java");

        try {

            String FILE_CONTENT = "System.out.println(\"Hello, World!\");";
            Files.write(FILE, FILE_CONTENT.getBytes());
            System.out.println("Content written to file.");

            byte[] CONTENT = Files.readAllBytes(FILE);
            System.out.println("File content: " + new String(CONTENT));


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

        // PATH_FUNCTION();
        FILE_IO_FUNCTION();
    }


}
