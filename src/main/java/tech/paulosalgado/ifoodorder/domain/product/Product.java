package tech.paulosalgado.ifoodorder.domain.product;

import lombok.*;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Product {

    @Id
    private UUID id;

    private String name;
    private BigDecimal price;

    public static class Builder {

        private UUID id;
        private String name;
        private BigDecimal price;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Builder withID(UUID id) throws ProductCreationException {

            if (id == null) {
                throw new ProductCreationException("id must be valid");
            }

            this.id = id;
            return this;
        }

        public Builder withName(String name) throws ProductCreationException {

            if (name == null || name.isEmpty()) {
                throw new ProductCreationException("name must be valid");
            }

            this.name = name;
            return this;
        }

        public Builder withPrice(BigDecimal price) throws ProductCreationException {

            if (price == null || price.doubleValue() < 0) {
                throw new ProductCreationException("price must be valid");
            }

            this.price = price;
            return this;
        }

        public Product build() {
            return new Product(this.id, this.name, this.price);
        }

    }

}
