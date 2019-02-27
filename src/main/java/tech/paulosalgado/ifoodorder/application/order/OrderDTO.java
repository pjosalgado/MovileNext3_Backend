package tech.paulosalgado.ifoodorder.application.order;

import lombok.*;
import tech.paulosalgado.ifoodorder.application.customer.CustomerDTO;
import tech.paulosalgado.ifoodorder.application.product.ProductDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OrderDTO {

    private UUID id;
    private CustomerDTO customer;
    private List<ProductDTO> products;
    private String paymentMethod;
    private BigDecimal totalWithDiscounts;
    private LocalDateTime date;

}
