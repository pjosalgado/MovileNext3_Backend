package tech.paulosalgado.ifoodorder.domain.product;

import tech.paulosalgado.ifoodorder.domain.product.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll();
    Product findById(UUID id) throws ProductNotFoundException;

    Product save(Product product);

}
