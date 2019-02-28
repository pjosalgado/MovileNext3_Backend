package tech.paulosalgado.ifoodorder.application.product;

import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.util.UUID;

public abstract class ProductFactory {

    public static Product getProduct(ProductDTO productDTO) throws ProductCreationException {
        return new Product.Builder()
                .withName(productDTO.getName())
                .withPrice(productDTO.getPrice())
                .build();
    }

    public static Product getProduct(UUID productId) throws ProductCreationException {
        return new Product.Builder()
                .withProductId(productId)
                .build();
    }

    public static ProductDTO getDTO(Product product) {
        return ProductDTO.builder()
                .productId(product.getProductId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

}
