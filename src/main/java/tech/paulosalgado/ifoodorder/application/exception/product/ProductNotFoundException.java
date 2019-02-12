package tech.paulosalgado.ifoodorder.application.exception.product;

public class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String id) {
        super("Product not found: " + id);
    }

}
