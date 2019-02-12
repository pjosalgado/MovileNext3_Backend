package tech.paulosalgado.ifoodorder.domain;

import java.util.List;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(String id);

    Product save(Product product);

}
