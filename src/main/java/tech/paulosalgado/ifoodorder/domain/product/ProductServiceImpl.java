package tech.paulosalgado.ifoodorder.domain.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product find(UUID id) {
        return repository.findById(id);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

}
