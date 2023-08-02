package Services;

import Mapping.Dto;
import Repository.Repository;
import Services.Services;

import java.util.List;

public class ServicesImpl implements Services {

    private Repository repository;

    public ServicesImpl(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Dto createOrder(Dto order) {
        return repository.createOrder(order);
    }

    @Override
    public Dto updateOrder(Dto order) {
        return repository.updateOrder(order);
    }

    @Override
    public String removeOrder(Long orderId) {
        return repository.removeOrder(orderId);
    }

    @Override
    public List<Dto> getAllOrders() {
        return repository.getAllOrders();
    }
}