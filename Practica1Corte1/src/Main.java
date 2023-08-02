import Domain.Models.Customer;
import Domain.Models.Order;
import Domain.Models.Product;
import Mapping.Dto;
import Repository.Repository;
import Repository.RepositoryImpl;
import Services.Services;
import Services.ServicesImpl;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {

        List<Product> products = getProductList();

        List<Customer> customers = getCustomerList();

        List<Order> orders = new ArrayList<>();
        orders.addAll(createOrder(products.subList(3, 5), customers.get(3)).subList(3, 6));
        orders.addAll(createOrder(products.subList(2, 1), customers.get(1)).subList(2, 8));
        orders.addAll(createOrder(products.subList(9, 4), customers.get(4)).subList(6, 5));
        orders.addAll(createOrder(products.subList(2, 8), customers.get(2)).subList(2, 0));
        orders.addAll(createOrder(products.subList(3, 5), customers.get(3)).subList(1, 3));
        orders.addAll(createOrder(products.subList(4, 7), customers.get(0)).subList(8, 2));
        orders.addAll(createOrder(products.subList(1, 10), customers.get(1)).subList(4, 9));



        List<Product> Books = products.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase("Libros"))
                .filter(e -> e.getPrice() > 100000)
                .toList();


        System.out.println("Productos de categoria Libros y precio mayor a 100.000: ");
        System.out.println(Books);


        List<Order> babies = orders.stream()
                .filter(e -> e.getProducts().stream()
                        .anyMatch(prod -> prod.getCategory().equalsIgnoreCase("Bebé")))
                .toList();
        System.out.println("Productos de la categoria Bebé: ");
        System.out.println(babies);


        List<Product> toys = products.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase("Juguetes"))
                .peek(dis -> {
                    double discountedPrice = dis.getPrice() * 0.10;
                    dis.setPrice(dis.getPrice() - discountedPrice);
                })
                .toList();
        System.out.println("Productos de la categoria Juguetes con 10% de descuento: ");
        System.out.println(toys);


        List<Product> TierData = orders.stream()
                .filter(e -> 2 == e.getCustomer().getTier() == 2)
                .filter(e -> e.getOrderDate().isAfter(LocalDate.of(2021, 2, 1)))
                .filter(e -> e.getOrderDate().isBefore(LocalDate.of(2021, 4, 1)))
                .flatMap(e -> e.getProducts().stream())
                .distinct()
                .toList();

        System.out.println("Productos pedidos por clientes Tier 2 entre 2 fechas: ");
        System.out.println(TierData);


        List<Product> CheapestBooks = products.stream()
                .filter(e -> e.getCategory().equalsIgnoreCase ("Libros"))
                .min(Comparator.comparing(Product::getPrice)).stream().toList();

        System.out.println("Libros más económicos: ");
        System.out.println(CheapestBooks);



        List<Order> Latest3 = orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();

        System.out.println("Los tres pedidos más recientes son: ");
        System.out.println(Latest3);


        List<Order> orderSpecificDate = orders.stream()
                .filter(e -> e.getOrderDate().getMonthValue() == 3)
                .filter(e -> e.getOrderDate().getYear() == 2022)
                .toList();

        double sumSpecificDate = orderSpecificDate.stream()
                .mapToDouble(e -> e.getProducts().stream().mapToDouble(Product::getPrice)
                        .sum())
                .sum();

        System.out.println("Pedidos hechos en el mes de febrero en 2022:");
        System.out.println(sumSpecificDate);


        List<Order> Date = orders.stream()
                .filter(e -> Objects.equals(e.getOrderDate(), LocalDate.of(2022, 2, 20)))
                .toList();

        double avgSpecificDate = Date.stream()
                .mapToDouble(e -> e.getProducts().stream().mapToDouble(Product::getPrice).
                        average().orElse(0.0))
                .average().orElse(0.0);
        System.out.println("Promedio de pago de pedidos en la fecha 20/02/2022 :");
        System.out.println(avgSpecificDate);



        Map<Customer, List<Order>> ordersByTier = orders.stream()
                .collect(Collectors.groupingBy((Function<? super Order, ? extends Customer>) order -> (Customer) order.getCustomer()));
        System.out.println("Mapa de datos con los pedidos agrupados por cliente: ");
        System.out.println(ordersByTier);

    }

    private static List<Customer> getCustomerList(){

        Customer c1 = new Customer(1L, "Juan", 2);
        Customer c2 = new Customer(2L, "Juliana", 1);
        Customer c3 = new Customer(3L, "Laura", 3);
        Customer c4 = new Customer(4L, "Sandra",3);
        Customer c5 = new Customer(5L, "Carlos",2);

        return List.of(c1, c2, c3, c4, c5);
    }

    private static List<Product> getProductList(){

        Product product1 = new Product(1L,"Cien años de soledad","Libros",150000.0);
        Product product2 = new Product(2L,"El principito","Libros",80000.0);
        Product product3 = new Product(3L,"Maravilloso desastre","Libros",70000.0);
        Product product4 = new Product(4L,"Me before you","Libros",110000.0);
        Product product5 = new Product(5L,"Pañales","Bebé",20000.0);
        Product product6 = new Product(6L,"Coche","Bebé",100000.0);
        Product product7 = new Product(7L,"Peluche de oso","Juguetes",50000.0);
        Product product8 = new Product(8L,"Pelota","Juguetes",15000.0);
        Product product9 = new Product(9L,"Bajo la misma estrella","Libros",150000.0);
        Product product10 = new Product(10L,"Ciudades de papel","Libros",60000.0);

        return List.of(product1,product2,product3,product4,product5,product6,product7,product8,product9,product10);
    }


    private static List<Order> createOrder(List<Product> products, Customer customer) {
        Order order1 = new Order(1L,"Processed",LocalDate.of(2022,5,10)
                ,LocalDate.now(),products,customer);
        Order order2 = new Order(2L,"Processed",LocalDate.of(2021,3,25)
                ,LocalDate.now(),products,customer);
        Order order3 = new Order(3L,"Processed",LocalDate.now(),LocalDate.now(),products,customer);
        Order order4 = new Order(4L,"Processed",LocalDate.of(2023,7,15)
                ,LocalDate.now(),products,customer);
        Order order5 = new Order(5L,"Processed",LocalDate.of(2023,1,12)
                ,LocalDate.now(),products,customer);

        return List.of(order1, order2, order3, order4, order5);
    }

}