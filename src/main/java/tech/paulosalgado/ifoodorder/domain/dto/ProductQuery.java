package tech.paulosalgado.ifoodorder.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class ProductQuery {

    private String id;
    private String name;
    private BigDecimal price;

}
