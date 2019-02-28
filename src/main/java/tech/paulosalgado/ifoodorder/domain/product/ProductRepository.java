package tech.paulosalgado.ifoodorder.domain.product;

import java.util.List;
import java.util.UUID;

public interface ProductRepository {

    List<Product> findAll();
    Product findById(UUID productId);

    Product save(Product product);

}
