package tech.paulosalgado.ifoodorder.infrastructure.repository.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.paulosalgado.ifoodorder.domain.order.Order;
import tech.paulosalgado.ifoodorder.domain.order.OrderRepository;

import java.util.UUID;

@Repository
public class OrderRepositoryDB implements OrderRepository {

    @Autowired
    private OrderRepositorySpringData repository;

    public Order findById(UUID orderId) {
        return repository.findById(orderId).orElse(null);
    }

    public Order save(Order order) {
        return repository.save(order);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
