package dop7;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class Base {
    private HashMap<String, Integer> settings;

    public Base() {
        this.settings = new HashMap<String, Integer>();
    }

    public void put(String line) {
        String[] parts = line.split(" ");
        String key = parts[0] + " " + parts[1] + " " + parts[2];
        int value = Integer.parseInt(parts[3]);
        this.settings.put(key, value);
    }

    public int get(String key) {
        return this.settings.get(key);
    }

    public void remove(String key) {
        this.settings.remove(key);
    }

    public static long getFileSize(String filename) {
        File file = new File(filename);
        return file.length();
    }

    public void saveToBinaryFile(String path) throws IOException {
        Path p = Paths.get(path);
        try {
            Files.writeString(p, this.settings.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadFromBinaryFile(String path) throws IOException {
        try {
            byte[] content = Files.readAllBytes(Paths.get(path));
            String line = new String(content);
            this.settings = new HashMap<String, Integer>();
            String[] pairs = line.substring(1, line.length()-1).split(", ");
            for (String pair : pairs) {
                String[] keyValue = pair.split("=");
                this.settings.put(keyValue[0], Integer.parseInt(keyValue[1]));
            }
        } catch (Exception e) {
            throw new IOException(e.getMessage());
        }
    }

    public void saveToTextFile(String filename) throws IOException {
        try {
            PrintWriter out = new PrintWriter(new FileWriter(filename));
            out.println(this);
            out.flush();
            out.close();
        } catch (Exception e) {
            throw new IOException("Error while saving to text file: " + e.getMessage());
        }
    }

    public void loadFromTextFile(String filename) throws IOException {
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
        } catch (Exception e) {
            throw new IOException("Error while loading from text file: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        String lineSeparator = System.getProperty("line.separator");
        String result = "";
        for (String key : this.settings.keySet()) {
            result += key + "=" + this.settings.get(key) + lineSeparator;
        }
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != this.getClass()) {
            return false;
        }
        Base other = (Base) obj;
        return this.settings.equals(other.settings);
    }

    @Override
    public int hashCode() {
        return settings.hashCode();
    }

}
