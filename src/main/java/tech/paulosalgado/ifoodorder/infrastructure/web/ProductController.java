package tech.paulosalgado.ifoodorder.infrastructure.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.application.product.ProductDTO;
import tech.paulosalgado.ifoodorder.application.product.ProductFactory;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductNotFoundException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.ProductService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@Api(tags = "Product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {

        List<Product> products = service.findAll();

        return ResponseEntity.status(HttpStatus.OK)
                .body(products.stream()
                        .map(product -> ProductFactory.getDTO(product))
                        .collect(Collectors.toList()));
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") UUID productId) throws ProductNotFoundException {

        Product product = service.findById(productId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ProductFactory.getDTO(product));
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> postProduct(@RequestBody ProductDTO productDTO) throws ProductCreationException {

        Product product = ProductFactory.getProduct(productDTO);
        product = service.save(product);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProductFactory.getDTO(product));
    }

}
