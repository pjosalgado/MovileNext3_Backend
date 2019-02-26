package tech.paulosalgado.ifoodorder.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductNotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(UUID id) throws ProductNotFoundException {

        Product product = repository.findById(id);

        if (product == null) {
            throw new ProductNotFoundException(id);
        }

        return product;
    }

    public Product save(Product product) {
        return repository.save(product);
    }

}
