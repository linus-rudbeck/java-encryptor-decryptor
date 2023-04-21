package se.distansakademin;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

    private static final String FILE_LOCATION = "C:\\Users\\rudbe\\Desktop\\encdec\\";
    static Scanner scanner = new Scanner(System.in);


    public static void main(String[] args) {
        System.out.println("Starting...");

        var AESEncDec = new AESEncryptionDecryption();

        System.out.println("encrypt/decrypt?");
        var selection = scanner.nextLine();

        System.out.println("Secret?");
        var secret = scanner.nextLine();

        if(selection.equals("encrypt")){
            // Read from decrypted
            var decrypted = readFromDecrypted();

            // Encrypt to string
            var encrypted = AESEncDec.encrypt(decrypted, secret);

            // Print to console
            System.out.println(encrypted);

            // Save to encrypted
            writeToEncrypted(encrypted);
        } else if (selection.equals("decrypt")) {
            // Read from encrypted
            var encrypted = readFromEncrypted();

            // Decrypt to string
            var decrypted = AESEncDec.decrypt(encrypted, secret);

            // Print to console
            System.out.println(decrypted);

            // Save to decrypted
            writeToDecrypted(decrypted);
        }

    }

    private static String readFromEncrypted() {
        return readFromFile("encrypted.txt");
    }

    private static String readFromDecrypted() {
        return readFromFile("decrypted.txt");
    }

    private static String readFromFile(String fileName) {
        String fileContent = "";

        try {
            // pass the path to the file as a parameter
            File file = new File(FILE_LOCATION + fileName);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                fileContent += sc.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found for reading");
        }

        return fileContent;
    }

    private static void writeToEncrypted(String content){
        writeToFile("encrypted.txt", content);
    }

    private static void writeToDecrypted(String content){
        writeToFile("decrypted.txt", content);
    }

    private static void writeToFile(String fileName, String content){

        try{
            FileOutputStream out = new FileOutputStream(FILE_LOCATION + fileName);
            out.write(content.getBytes(StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            System.out.println("File " + fileName + " not found for writing");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}