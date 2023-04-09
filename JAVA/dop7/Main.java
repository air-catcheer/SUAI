package dop7;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Base base = new Base();
        base.put("Abramova Valeriya Victorovna 21");
        base.put("Andreev Andrey Andreevich 4");
        base.put("Efimova Anastasiya Andreevna 28");
        base.put("Frankov Victor Alekseevich 33");
        base.put("Ivanov Ivan Ivanovich 25");
        base.put("Levina Larisa Igorevna 52");

        File createFile = new File("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.txt");

        try {
            createFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File createFile2 = new File("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.bin");

        try {
            createFile2.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            base.saveToBinaryFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.bin");
            Base settingsFromBinFile = new Base();
            settingsFromBinFile.loadFromBinaryFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.bin");
            System.out.println("Data from binary file:");
            System.out.println(settingsFromBinFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Data from text file:");
        try {
            base.saveToTextFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.txt");
            Base settingsFromTxtFile = new Base();
            settingsFromTxtFile.loadFromTextFile("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.txt");
            System.out.println(settingsFromTxtFile);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Size of text file: " + Base.getFileSize("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.txt") + " bytes");
        System.out.println("Size of binary file: " + Base.getFileSize("C:\\GUAP\\LAB_JAVA\\LABS\\src\\dop7\\base.bin") + " bytes");
    }
}
