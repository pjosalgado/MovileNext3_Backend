package tech.paulosalgado.ifoodorder.domain.order;

import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderNotFoundException;

import java.util.UUID;

public interface OrderService {

    Order findById(UUID orderId) throws OrderNotFoundException;

    Order save(Order order) throws OrderCreationException;

}
