package Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import Domain.Models.Customer;
import Domain.Models.Order;
import Mapping.Dto;
import Mapping.Mapper;
import Repository.Repository;

public class RepositoryImpl implements Repository {

    List<Order> orders;

    public RepositoryImpl() {
        orders = new ArrayList<>();
    }

    @Override
    public Dto createOrder(Dto order) {
        orders.add(Mapper.mapFrom(order));
        return order;
    }

    @Override
    public Dto updateOrder(Dto order) {
        Order orderSaved = findById(order.identifier());
        orderSaved.setStatus(order.status());
        orderSaved.setOrderDate(order.orderDate());
        orderSaved.setDeliveryDate(order.delivery());
        orderSaved.setProducts(order.products());
        orderSaved.setCustomer((List<Customer>) order.customer());
        updateOrderInList(orderSaved);
        return Mapper.mapFrom(orderSaved);
    }

    private void updateOrderInList(Order order){
        orders.remove(order);
        orders.add(order);
    }

    @Override
    public String removeOrder(Long orderId) {
        orders.remove(findById(orderId));
        return "¡User deleted!"+orders.toString();
    }

    @Override
    public List<Dto> getAllOrders() {
        return Mapper.mapFrom(orders);
    }

    @Override
    public Dto findOrderById(Long orderId) {
        return Mapper.mapFrom(findById(orderId));
    }

    private Order findById(Long identifier) {
        for(Order order : orders){
            if(Objects.equals(order.getId(), identifier)){
                return order;
            }
        }
        throw new RuntimeException("Client not found");
    }
}