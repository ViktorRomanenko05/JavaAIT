package homeworkLesson50;

import java.util.HashMap;

public class ProductManager {

    private HashMap <String, Product> products = new HashMap<>();

    public HashMap<String, Product> getProducts() {
        return new HashMap<>(products);
    }
}
