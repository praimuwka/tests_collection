import static utils.DataGenerator.COOK_BOOK_DELIMITER;
import static utils.DataGenerator.COOK_BOOK_FILE_PATH;
import static utils.DataGenerator.MIXES_DELIMITER;
import static utils.FileIOUtils.readStringsFromFile;

import java.io.*;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import common.AbstractProduct;

/**
 * Класс NomiaTask представляет собой приложение, которое тестирует алгоритмы обработки данных о продуктах.
 * Приложение считывает данные из файла, создает список ингридиентов и их смесей, а затем
 * выполняет операции над продуктами, используя два разных подхода.
 */
public class NomiaTask {
    /**
     * Точка входа в приложение.
     * Считывает данные о ингридиентов и их смесях из файла, создает списки продуктов и смесей,
     * выполняет операции над продуктами и выводит результаты.
     *
     * @param args аргументы командной строки (не используются)
     * @throws IOException в случае ошибок при чтении файла
     */
    public static void main(String[] args) throws IOException {
        // Считывание данных о продуктах и их смесях из файла
        List<String> cookBookDraft = readStringsFromFile(COOK_BOOK_FILE_PATH);

        // Создание списка имен продуктов
        ArrayList<String> productNames = cookBookDraft.stream()
            .map(p -> p.split(COOK_BOOK_DELIMITER)[0])
            .collect(Collectors.toCollection(ArrayList::new));

        // Создание списка смесей для каждого продукта
        ArrayList<List<Integer>> productMixes = cookBookDraft.stream()
            .map(p -> {
                String[] parts = p.split(COOK_BOOK_DELIMITER);
                if (parts.length < 2) {
                    return Collections.<Integer>emptyList();
                }
                return Arrays.stream(parts[1].split(MIXES_DELIMITER))
                    .map(Integer::parseInt)
                    .toList();
            })
            .collect(Collectors.toCollection(ArrayList::new));

        // Создание списков продуктов для двух разных подходов
        ArrayList<approach1.Product> products1 = createProducts(approach1.Product::new, productNames);
        ArrayList<approach2.Product> products2 = createProducts(approach2.Product::new, productNames);

        // Вывод общего количества продуктов
        System.out.println("\nВсего продуктов: " + productNames.size());

        // Выполнение операций над продуктами для первого подхода
        System.out.println("-- Способ 1 (нисходящий обход) -------------------------------");
        completeMixes(products1, productMixes);

        // Выполнение операций над продуктами для второго подхода
        System.out.println("-- Способ 2 (восходящий обход) -------------------------------");
        completeMixes(products2, productMixes);

        System.out.println();
    }

    /**
     * Создает список продуктов типа T, расширяющего AbstractProduct<T>, из списка строк.
     * Каждая строка в входном списке преобразуется в продукт с использованием предоставленной функции маппера.
     */
    private static <T extends AbstractProduct<T>> ArrayList<T> createProducts(Function<? super String, ? extends T> mapper, List<String> productNames) {
        return productNames.stream()
            .map(mapper)
            .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Метод выполняет операции над продуктами, используя список смесей.
     * Для каждого продукта пытается добавить его в смеси, указанные в списке смесей, в качестве ингридиента.
     * Выводит статистику, которая включает в себя время выполнения алгоритма и количество успешных и потенциальных связей.
     *
     * @param products   список продуктов
     * @param mixesLists список смесей для каждого продукта
     * @param <T>        тип продукта, должен быть подклассом AbstractProduct
     */
    private static <T extends AbstractProduct<T>> void completeMixes(ArrayList<T> products, ArrayList<List<Integer>> mixesLists) {
        int successCounter = 0;
        long startTime = System.nanoTime();
        for (int i = 0; i < products.size(); i++) {
            T ingredient = products.get(i);
            List<Integer> mixes = mixesLists.get(i);
            for (Integer mixIdx : mixes) {
                T mix = products.get(mixIdx);
                boolean res = mix.addProduct(ingredient); // пробуем добавить ингридиент в смесь
                if (res) {
                    successCounter++;
                }
            }
        }
        long stopTime = System.nanoTime();
        System.out.printf("Execution time: %s ms %n", (stopTime - startTime) / 1000000);
        System.out.println("Всего потенциальных связей: " + mixesLists.stream().mapToInt(List::size).sum());
        System.out.println("Всего успешных связей: " + successCounter);
    }
}
