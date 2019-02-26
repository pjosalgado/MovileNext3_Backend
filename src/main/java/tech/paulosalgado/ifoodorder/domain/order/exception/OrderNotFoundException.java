package tech.paulosalgado.ifoodorder.domain.order.exception;

import java.util.UUID;

public class OrderNotFoundException extends Exception {

    public OrderNotFoundException(UUID id) {
        super("order not found: " + id);
    }

}
