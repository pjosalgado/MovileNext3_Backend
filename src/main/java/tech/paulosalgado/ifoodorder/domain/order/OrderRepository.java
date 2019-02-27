package tech.paulosalgado.ifoodorder.domain.order;

import java.util.UUID;

public interface OrderRepository {

    Order findById(UUID id);

    Order save(Order order);

}
