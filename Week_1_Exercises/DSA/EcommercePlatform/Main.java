package EcommercePlatform;

public class Main {
    public static void main(String[] args) {
        Product[] products = {
                new Product("P001", "Laptop", "Electronics"),
                new Product("P002", "Smartphone", "Electronics"),
                new Product("P003", "Tablet", "Electronics"),
                new Product("P004", "Headphones", "Accessories"),
                new Product("P005", "Charger", "Accessories")
        };

        // Linear Search
        Product linearResult = LinearSearch.linearSearch(products, "P003");
        System.out.println("Linear Search Result: " + (linearResult != null ? linearResult : "Product not found"));

        // Binary Search
        Product binaryResult = BinarySearch.binarySearch(products, "P003");
        System.out.println("Binary Search Result: " + (binaryResult != null ? binaryResult : "Product not found"));
    }
}
