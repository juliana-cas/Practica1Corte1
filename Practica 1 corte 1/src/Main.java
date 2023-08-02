import Domain.models.Product;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Product> products = getProducts();
    }

    private static List<Product> getProducts() {
        Product product1 = new Product(1L, "Libro1", "Libro", 50.000);
        Product product2 = new Product(2L, "Libro2", "Libro", 35.000);
        Product product3 = new Product(3L, "Libro3", "Libro", 45.000);
        return List.of(product1, product2);
    }
    private static List<Costumer> getCostumerList(){
        Costumer costumer1 = new Costumer(1L, "Juan", 1);
        Costumer costumer2 = new Costumer(2L, "Juliana", 2);
        Costumer costumer3 = new Costumer(3L, "Sandra", 3);
        return List.of(costumer1, costumer2);
    }
}