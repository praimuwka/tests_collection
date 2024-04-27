package approach1;

import java.util.*;

import common.AbstractProduct;

/**
 * Класс Product представляет собой продукт, который может быть частью смеси.
 * Реализует логику добавления продукта в смесь с использованием обхода сверху вниз.
 */
public class Product extends AbstractProduct<Product> {
    /**
     * Конструктор класса Product.
     * Создает новый продукт с заданным именем.
     *
     * @param name имя продукта
     */
    public Product(String name) {
        super(name);
    }

    /**
     * Добавляет продукт в смесь текущего продукта, если это возможно без создания циклической зависимости.
     * Использует обход сверху вниз для поиска текущего продукта в составе добавляемого ингридиента.
     *
     * @param ingredient продукт, который нужно добавить в смесь
     * @return true, если продукт успешно добавлен, false в случае угрозы циклической зависимости
     */
    public boolean addProduct(Product ingredient) {
        var container = this;
        var content = ingredient;

        if (content.contains(container, new HashSet())) {
            return false;
        }

        children.add(content);
        return true;
    }

    /**
     * Рекурсивно проверяет, содержит ли текущий продукт указанный продукт в иерархии ингридиентов.
     * Использует обход сверху вниз для поиска продукта.
     *
     * @param target       продукт, который нужно найти
     * @param visitedNodes набор уже посещенных продуктов
     * @return true, если продукт найден, false в противном случае
     */
    public boolean contains(Product target, Set<Product> visitedNodes) {
        if (visitedNodes.contains(this)) {
            return false; // Если узел уже был посещен, не обходим его повторно
        }

        visitedNodes.add(this); // Помечаем узел как посещенный

        if (this.getChildren().contains(target)) {
            return true; // Если искомый элемент найден в списке потомков текущего узла
        }

        for (Product child : this.getChildren()) {
            if (child.contains(target, visitedNodes)) {
                return true; // Если элемент найден в одном из потомков, возвращаем true
            }
        }
        return false; // Если элемент не найден в дереве
    }
}
