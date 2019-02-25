package tech.paulosalgado.ifoodorder.domain.product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    List<Product> findAll();
    Product find(UUID id);

    Product save(Product product);

}
