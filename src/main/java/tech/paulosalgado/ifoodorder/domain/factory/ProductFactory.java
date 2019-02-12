package tech.paulosalgado.ifoodorder.domain.factory;

import tech.paulosalgado.ifoodorder.domain.Product;
import tech.paulosalgado.ifoodorder.domain.dto.ProductCommand;
import tech.paulosalgado.ifoodorder.domain.dto.ProductQuery;
import tech.paulosalgado.ifoodorder.application.exception.product.ProductCreationException;

public abstract class ProductFactory {

    public static Product getProduct(ProductCommand command) throws ProductCreationException {
        return new Product.Builder()
                .withName(command.getName())
                .withPrice(command.getPrice())
                .build();
    }

    public static ProductQuery getQuery(Product product) {
        return ProductQuery.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .build();
    }

}
