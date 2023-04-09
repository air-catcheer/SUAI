package lab7;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Settings settings = new Settings();

        settings.put("width", 640);
        settings.put("height", 480);
        settings.put("fps", 30);


        File createFile = new File("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.txt");

        try {
            createFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File createFile2 = new File("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.bin");

        try {
            createFile2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Initial settings:");

        try {
            settings.saveToBinaryFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.bin");
            Settings settingsFromBinFile = new Settings();
            settingsFromBinFile.loadFromBinaryFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.bin");
            System.out.println("* Settings from bin file:");
            System.out.println(settingsFromBinFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        try {
            settings.saveToTextFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.txt");
            Settings settingsFromTxtFile = new Settings();
            settingsFromTxtFile.loadFromTextFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.txt");
            System.out.println("* Settings from txt file:");
            System.out.println(settingsFromTxtFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //удалить параметр fps
        settings.delete("fps");
        //добавить новый параметр colorDepth
        settings.put("colorDepth", 32);

        System.out.println();
        System.out.println("Changed settings:");

        //загрузить из файла settings.bin
        try {
            settings.saveToBinaryFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.bin");
            Settings settingsFromBinFile = new Settings();
            settingsFromBinFile.loadFromBinaryFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.bin");
            System.out.println("* Settings from bin file:");
            System.out.println(settingsFromBinFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //загрузить из файла settings.txt
        try {
            settings.saveToTextFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.txt");
            Settings settingsFromTxtFile = new Settings();
            settingsFromTxtFile.loadFromTextFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\lab7\\settings.txt");
            System.out.println("* Settings from txt file:");
            System.out.println(settingsFromTxtFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
