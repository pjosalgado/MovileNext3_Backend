package tech.paulosalgado.ifoodorder.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.application.product.ProductDTO;
import tech.paulosalgado.ifoodorder.application.product.ProductFactory;
import tech.paulosalgado.ifoodorder.application.product.exception.ProductCreationException;
import tech.paulosalgado.ifoodorder.application.product.exception.ProductNotFoundException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public List<ProductDTO> getProducts() {

        List<Product> products = service.findAll();

        return products.stream()
                .map(product -> ProductFactory.getDTO(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/products/{id}")
    public ProductDTO getProduct(@PathVariable("id") UUID id) throws ProductNotFoundException {

        Product product = service.find(id);

        if (product != null) {
            return ProductFactory.getDTO(product);
        } else {
            throw new ProductNotFoundException(id);
        }
    }

    @PostMapping("/products")
    public ProductDTO postProduct(@RequestBody ProductDTO productDTO) throws ProductCreationException {

        Product product = ProductFactory.getProduct(productDTO);
        product = service.save(product);

        return ProductFactory.getDTO(product);
    }

}
