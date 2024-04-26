package approach2;

import common.AbstractProduct;

public class Product extends AbstractProduct<Product> {
    public Product(String name) {
        super(name);
    }

    public boolean addProduct(Product oneMore) {
        var x = false;
        return x && children.add(oneMore);
    }
}