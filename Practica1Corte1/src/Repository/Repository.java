package Repository;
import Mapping.Dto;
import java.util.List;


public interface Repository {
    Dto createOrder(Dto order);
    Dto updateOrder(Dto order);
    String removeOrder(Long orderId);
    List<Dto> getAllOrders();
    Dto findOrderById(Long orderId);
}
