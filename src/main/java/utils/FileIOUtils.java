package utils;

import java.io.*;
import java.util.*;

public class FileIOUtils {

    public static void writeStringsToFile(String filePath, List<String> strings) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String string : strings) {
                writer.write(string);
                writer.newLine(); // Writes a new line after each string
            }
        }
    }

    public static List<String> readStringsFromFile(String filePath) throws IOException {
        List<String> strings = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                strings.add(line);
            }
        }
        return strings;
    }
}

