package Services;

import Mapping.Dto;
import java.util.List;
public interface Services {
    Dto createOrder(Dto order);
    Dto updateOrder(Dto order);
    String removeOrder(Long orderId);
    List<Dto> getAllOrders();
}