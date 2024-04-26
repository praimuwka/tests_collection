package common;

import java.util.*;

public abstract class AbstractProduct<P extends AbstractProduct> {
    protected String name;
    protected HashSet<P> children;
    protected AbstractProduct(String name) {
        this.name = name;
        children = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public HashSet<P> getChildren() {
        return children;
    }
    public abstract boolean addProduct(P product);
}
