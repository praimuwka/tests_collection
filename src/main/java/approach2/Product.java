package approach2;

import java.util.*;

import common.AbstractProduct;

/**
 * Класс Product представляет собой продукт, который может быть частью смеси.
 * Реализует логику добавления продукта в смесь, проверяя наличие циклических зависимостей.
 */
public class Product extends AbstractProduct<Product> {
    // Список асцендентных (восходящих) связей между продуктами
    // Каждый i-тый список внутри ASCENDING_RELATIONS, хранит набор идентификаторов смесей, для которых i-тый продукт является ингридиентом
    // Индекс списка смесей соответствует идентификатору продукта 
    private static final ArrayList<ArrayList<Integer>> ASCENDING_RELATIONS = new ArrayList<>();
    // Идентификатор продукта
    private int id;

    /**
     * Конструктор класса Product.
     * Создает новый продукт с уникальным идентификатором и добавляет его в список связей "Ингридиент -> Смесь".
     *
     * @param name имя продукта
     */
    public Product(String name) {
        super(name);
        id = ASCENDING_RELATIONS.size();
        ASCENDING_RELATIONS.add(new ArrayList<>());
    }

    /**
     * Добавляет продукт в смесь текущего продукта, если это приведет к возникновению циклической зависимости.
     * Использует обход снизу вверх для проверки наличия восходящего маршрута от текущего продута к добавляемому.
     *
     * @param ingredient продукт, который нужно добавить в смесь
     * @return true, если продукт успешно добавлен, false в случае угрозы циклической зависимости
     */
    public boolean addProduct(Product ingredient) {
        if (isMix(ingredient.id, this.id, new HashSet<>())) {
            return false;
        }
        children.add(ingredient);
        ASCENDING_RELATIONS.get(ingredient.id).add(this.id);
        return true;
    }

    /**
     * Рекурсивно проверяет, является продукт ингридиентом для смеси.
     *
     * @param possibleMix  идентификатор продукта - возможной смеси
     * @param currentNode  текущий узел, потенциальный ингридиент
     * @param visitedNodes набор уже посещенных узлов
     * @return true, если найден маршрут от currentNode к possibleMix, false в противном случае
     */
    private static boolean isMix(Integer possibleMix, Integer currentNode, Set<Integer> visitedNodes) {
        if (visitedNodes.contains(currentNode)) {
            return false;
        }
        if (currentNode.equals(possibleMix)) {
            return true;
        }
        visitedNodes.add(currentNode);
        var mixesToCheck = ASCENDING_RELATIONS.get(currentNode);
        for (var mixId : mixesToCheck) {
            if (isMix(possibleMix, mixId, visitedNodes)) {
                return true;
            }
        }
        return false;
    }
}
