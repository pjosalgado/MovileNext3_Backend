package tech.paulosalgado.ifoodorder.domain.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderNotFoundException;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    public Order findById(UUID id) throws OrderNotFoundException {

        Order order = repository.findById(id);

        if (order == null) {
            throw new OrderNotFoundException(id);
        }

        return order;
    }

    public Order save(Order order) throws OrderCreationException {
        return repository.save(order);
    }

}
