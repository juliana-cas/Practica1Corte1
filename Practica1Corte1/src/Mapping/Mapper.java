package Mapping;

import Domain.Models.Order;
import java.util.List;

public class Mapper {
    public static Dto mapFrom(Order source) {
        return new Dto(source.getId(),
                source.getStatus(),
                source.getOrderDate(),
                source.getDeliveryDate(),
                source.getProducts(),
                source.getCustomer());
    }

    public static Order mapFrom(Dto source) {
        return new Order(source.identifier(),
                source.status(),
                source.orderDate(),
                source.delivery(),
                source.products(),
                source.customer());
    }

    public static List<Dto> mapFrom(List<Order> source) {
        return source.parallelStream().map(e-> mapFrom(e)).toList();
    }
}