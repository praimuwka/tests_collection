import static utils.DataGenerator.COOK_BOOK_DELIMITER;
import static utils.DataGenerator.COOK_BOOK_FILE_PATH;
import static utils.DataGenerator.MIXES_DELIMITER;
import static utils.FileIOUtils.readStringsFromFile;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import common.AbstractProduct;

public class NomiaTest {
    public static void main(String[] args) throws IOException {
        var cookBookDraft = readStringsFromFile(COOK_BOOK_FILE_PATH);
        var productNames = cookBookDraft.stream()
            .map(p -> p.split(COOK_BOOK_DELIMITER)[0])
            .collect(Collectors.toCollection(ArrayList::new));
        var productMixes = cookBookDraft.stream()
            .map(p -> {
                var parts = p.split(COOK_BOOK_DELIMITER);
                if (parts.length < 2) {
                    return Collections.<Integer>emptyList();
                }
                return Arrays.stream(parts[1].split(MIXES_DELIMITER))
                    .map(Integer::parseInt)
                    .toList();
            })
            .collect(Collectors.toCollection(ArrayList::new));
        var products1 = productNames.stream()
            .map(approach1.Product::new)
            .collect(Collectors.toCollection(ArrayList::new));
        var products2 = productNames.stream()
            .map(approach2.Product::new)
            .collect(Collectors.toCollection(ArrayList::new));
        completeMixes(products1, productMixes);
        // completeMixes(products2, productMixes);
    }

//todo метод вроде норм но не протестирован
    private static <T extends AbstractProduct<T>> void completeMixes(ArrayList<T> products, ArrayList<List<Integer>> mixesLists) {
        LinkedList<String> out = new LinkedList<>();
        long startTime = System.nanoTime();
        for (int i = 0; i < products.size(); i++) {
            var ingredient = products.get(i);
            var mixes = mixesLists.get(i);
            for (var mixIdx : mixes) {
                var mix = products.get(mixIdx);
                var res = mix.addProduct(ingredient); // пробуем добавить ингридиент в смесь
                out.add(String.format("%s -> %s = %s", ingredient.getName(), mix.getName(), res ? "1" : "0"));
            }
        }
        System.out.printf("Execution time: %s ms\n", (System.nanoTime() - startTime) / 1000000);
        System.out.println("ingredient   |  mix         | res");
        out.forEach(System.out::println);
    }
}
