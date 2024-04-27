package utils;

import static utils.FileIOUtils.writeStringsToFile;

import java.io.*;
import java.util.*;

/**
 * Класс DataGenerator генерирует данные о продуктах и их смесях, сохраняя их в файл.
 * Данные включают в себя имена продуктов и смеси, в которые они входят.
 */
public class DataGenerator {
    // Количество продуктов для генерации
    private static final int PRODUCTS_NUMBER = 10_000;
    // Количество цифр в формате для имен продуктов
    public static final int FORMAT_DIGITS = String.valueOf(Math.abs(PRODUCTS_NUMBER)).replaceFirst("^0*", "").length();
    // Диапазон для генерации количества смесей для каждого продукта
    private static final int MIXES_NUMBER_RANGE = 12; // чтобы получить от 0 до 10 смесей
    // Путь к файлу, в который будут записаны данные
    public static final String COOK_BOOK_FILE_PATH = "./src/main/resources/CookBookDraft.txt";
    // Разделитель между именем продукта и его смесями
    public static final String COOK_BOOK_DELIMITER = " - ";
    // Разделитель между индексами смесей
    public static final String MIXES_DELIMITER = ",";
    // Генератор случайных чисел
    private static final Random RANDOM = new Random();

    /**
     * Генерирует список имен продуктов заданного размера.
     *
     * @param size размер списка
     * @return список имен продуктов
     */
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

    /**
     * Добавляет случайные смеси к имени продукта.
     *
     * @param product        имя продукта
     * @param productIndex   индекс продукта в списке
     * @param productsNumber общее количество продуктов
     * @return строку с именем продукта и его смесями
     */
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

    /**
     * Точка входа в приложение.
     * Генерирует данные о продуктах и их смесях, сохраняя их в файл.
     *
     * @param args аргументы командной строки (не используются)
     * @throws IOException в случае ошибок при записи в файл
     */
    public static void main(String[] args) throws IOException {
        ArrayList<String> products = new ArrayList(generateProductNames(PRODUCTS_NUMBER).stream().distinct().toList());
        for (int i = 0; i < products.size(); i++) {
            String product = products.get(i);
            products.set(i, addRandomMixes(product, i, products.size()));
        }
        writeStringsToFile(COOK_BOOK_FILE_PATH, products);
    }
}
