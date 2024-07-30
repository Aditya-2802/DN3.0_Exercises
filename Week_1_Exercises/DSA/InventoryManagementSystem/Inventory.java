package InventoryManagementSystem;

import java.util.HashMap;

public class Inventory {
    private HashMap<String, Product> products;

    public Inventory() {
        products = new HashMap<>();
    }

    // Methods to add, update, and delete products
    public void addProduct(Product product) {
        products.put(product.getProductId(), product);
    }

    public void updateProduct(Product product) {
        if (products.containsKey(product.getProductId())) {
            products.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found");
        }
    }

    public void deleteProduct(String productId) {
        if (products.containsKey(productId)) {
            products.remove(productId);
        } else {
            System.out.println("Product not found");
        }
    }

    public Product getProduct(String productId) {
        return products.get(productId);
    }

    @Override
    public String toString() {
        return "Inventory [products=" + products + "]";
    }
}
