package tech.paulosalgado.ifoodorder.application.product;

import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class ProductDTO {

    private UUID id;
    private String name;
    private BigDecimal price;

}
