package Mapping;
import java.time.LocalDate;
import java.util.List;
import Domain.Models.Customer;
import Domain.Models.Product;

public record Dto (Long identifier,
                   String status,
                   LocalDate orderDate,
                   LocalDate delivery,
                   List<Product> products,
                   List<Customer> customer) {
}
