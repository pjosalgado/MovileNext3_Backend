package tech.paulosalgado.ifoodorder.domain.customer.exception;

import java.util.UUID;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(UUID id) {
        super("customer not found: " + id);
    }

}
