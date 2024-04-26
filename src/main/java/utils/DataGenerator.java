package utils;

import static utils.FileIOUtils.writeStringsToFile;

import java.io.*;
import java.util.*;

public class DataGenerator {
    private static final int PRODUCTS_NUMBER = 10000;
    public static final int FORMAT_DIGITS = String.valueOf(Math.abs(PRODUCTS_NUMBER)).replaceFirst("^0*", "").length();
    private static final int MIXES_NUMBER_RANGE = 7; // чтобы получить от 0 до 5 смесей
    public static final String COOK_BOOK_FILE_PATH = "./src/main/resources/CookBookDraft.txt";
    public static final String COOK_BOOK_DELIMITER = " - ";
    public static final String MIXES_DELIMITER = ",";
    private static final Random RANDOM = new Random();

    public static List<String> generateProductNames(int size) {
        if (size < 0) {
            return null;
        }
        List<String> randomStrings = new ArrayList<>();
        String mask = "Продукт %0" + FORMAT_DIGITS + "d";
        for (int i = 0; i < size; i++) {
            randomStrings.add(String.format(mask, i));
        }
        return randomStrings;
    }

    public static String addRandomMixes(String product, int productIndex, int productsNumber) {
        var mixesNum = RANDOM.nextInt(MIXES_NUMBER_RANGE) - 1;
        ArrayList<String> mixes = new ArrayList<>();
        for (int i = 0; i < mixesNum; i++) {
            var mixIdx = RANDOM.nextInt(productsNumber);
            if (mixIdx != productIndex) {
                mixes.add(mixIdx + "");
            }
        }
        return product + COOK_BOOK_DELIMITER + String.join(MIXES_DELIMITER, mixes);
    }

    public static void main(String[] args) throws IOException {
        ArrayList<String> products = new ArrayList(generateProductNames(PRODUCTS_NUMBER).stream().distinct().toList());
        for (int i = 0; i < products.size(); i++) {
            String product = products.get(i);
            products.set(i, addRandomMixes(product, i, products.size()));
        }
        writeStringsToFile(COOK_BOOK_FILE_PATH, products);
    }
}
