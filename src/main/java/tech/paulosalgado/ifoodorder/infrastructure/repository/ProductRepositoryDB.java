package tech.paulosalgado.ifoodorder.infrastructure.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.paulosalgado.ifoodorder.domain.Product;
import tech.paulosalgado.ifoodorder.domain.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepositoryDB implements ProductRepository {

    @Autowired
    private ProductRepositorySpringData repository;

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public Product findById(String id) {
        return repository.findById(id).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

}
