package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

public class Database {
    public static final String LOGFILE = "C:\\IdeaP\\Flood\\src\\logfile.txt";
    public void save(String text){
        try (FileWriter writer = new FileWriter(LOGFILE, true)){
            writer.write(text);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    public String read() {
        StringBuilder log = new StringBuilder();
        try (Scanner scanner = new Scanner(new File(LOGFILE))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Лог файл не найден.");
        }
        return log.toString();
    }
}
