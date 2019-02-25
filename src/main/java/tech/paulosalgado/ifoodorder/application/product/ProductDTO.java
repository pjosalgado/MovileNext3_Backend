package tech.paulosalgado.ifoodorder.application.product;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
@Getter
public class ProductDTO {

    private UUID id;
    private String name;
    private BigDecimal price;

}
