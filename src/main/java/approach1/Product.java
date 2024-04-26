package approach1;

import java.util.*;

import common.AbstractProduct;

public class Product extends AbstractProduct<Product> {
    public Product(String name) {
        super(name);
    }

    // обход сверху вниз
    public boolean addProduct(Product ingredient) {
        var container = this;
        var content = ingredient;

        if (content.contains(container, new HashSet())) {
            return false;
        }

        children.add(content);
        return true;
    }

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