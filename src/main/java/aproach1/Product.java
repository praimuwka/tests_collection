import java.util.*;

public class Product {
    private String name;
    private HashSet children;
    public Product(String name) {
        this.name = name;
        children = new HashSet<>();
    }

    public boolean addProduct(Product oneMore) {
        var x = false;
        return x && children.add(oneMore);
    }

    public static findChildLike()

    public static void main(String[] args) {

    }
}