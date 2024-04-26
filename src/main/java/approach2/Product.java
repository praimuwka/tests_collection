package approach2;

import java.util.*;

import common.AbstractProduct;

public class Product extends AbstractProduct<Product> {
    private static final ArrayList<ArrayList<Integer>> ASCENDING_RELATIONS = new ArrayList<>();
    private int id;

    public Product(String name) {
        super(name);
        id = ASCENDING_RELATIONS.size();
        ASCENDING_RELATIONS.add(new ArrayList<>());
    }

    public boolean addProduct(Product ingredient) {
        if (isMix(ingredient.id, this.id, new HashSet<>())) {
            return false;
        }
        children.add(ingredient);
        ASCENDING_RELATIONS.get(ingredient.id).add(this.id);
        return true;
    }

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