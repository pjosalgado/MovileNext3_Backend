package tech.paulosalgado.ifoodorder.infrastructure.repository.order;

import org.springframework.data.repository.CrudRepository;
import tech.paulosalgado.ifoodorder.domain.order.Order;

import java.util.UUID;

public interface OrderRepositorySpringData extends CrudRepository<Order, UUID> {

}
