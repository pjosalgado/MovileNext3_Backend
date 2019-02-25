package tech.paulosalgado.ifoodorder.application.product.exception;

import java.util.UUID;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(UUID id) {
        super("product not found: " + id);
    }

}
