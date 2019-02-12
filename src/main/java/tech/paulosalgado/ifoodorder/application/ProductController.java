package tech.paulosalgado.ifoodorder.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.domain.Product;
import tech.paulosalgado.ifoodorder.domain.ProductRepository;
import tech.paulosalgado.ifoodorder.domain.dto.ProductCommand;
import tech.paulosalgado.ifoodorder.domain.dto.ProductQuery;
import tech.paulosalgado.ifoodorder.domain.factory.ProductFactory;
import tech.paulosalgado.ifoodorder.application.exception.product.ProductCreationException;
import tech.paulosalgado.ifoodorder.application.exception.product.ProductNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

@RestController("/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    @GetMapping
    public List<ProductQuery> getProducts() {

        List<Product> products = repository.findAll();

        return products.stream()
                .map(product -> ProductFactory.getQuery(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductQuery getProduct(@PathVariable("id") String id) throws ProductNotFoundException {

        Product product = repository.findById(id);

        if (product != null) {
            return ProductFactory.getQuery(product);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @PostMapping
    public ProductQuery postProduct(@RequestBody ProductCommand command) throws ProductCreationException {

        Product product = ProductFactory.getProduct(command);
        product = repository.save(product);

        return ProductFactory.getQuery(product);
    }

}
