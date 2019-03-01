package tech.paulosalgado.ifoodorder.infrastructure.repository.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class ProductRepositoryDB implements ProductRepository {

    @Autowired
    private ProductRepositorySpringData repository;

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        repository.findAll().forEach(product -> products.add(product));
        return products;
    }

    public Product findById(UUID productId) {
        return repository.findById(productId).orElse(null);
    }

    public Product save(Product product) {
        return repository.save(product);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

}
