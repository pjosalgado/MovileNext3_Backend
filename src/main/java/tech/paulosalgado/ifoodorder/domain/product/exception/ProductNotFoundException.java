package tech.paulosalgado.ifoodorder.domain.product.exception;

import java.util.UUID;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(UUID id) {
        super("product not found: " + id);
    }

}
