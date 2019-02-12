package tech.paulosalgado.ifoodorder.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ProductCommand {

    private String name;
    private BigDecimal price;

}
