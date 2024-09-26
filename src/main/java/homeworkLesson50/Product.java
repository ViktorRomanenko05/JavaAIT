package homeworkLesson50;

import java.util.Objects;

public class Product {

    private static final long serialVersionUID = 1L;

    private String id;
    private String productName;
    private String productDescription;
    private int StockQuantity;
    private double price;

    public Product(String id, String productName, String productDescription, int stockQuantity, double price) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        StockQuantity = stockQuantity;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getStockQuantity() {
        return StockQuantity;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", productName='" + productName + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", StockQuantity=" + StockQuantity +
                ", price=" + price +
                '}';
    }
}
