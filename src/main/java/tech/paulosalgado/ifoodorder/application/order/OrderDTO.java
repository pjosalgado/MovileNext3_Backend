package tech.paulosalgado.ifoodorder.application.order;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@JsonInclude(Include.NON_NULL)
public class OrderDTO {

    private UUID orderId;
    private UUID customerId;
    private List<UUID> products;
    private String paymentMethod;
    private BigDecimal totalWithDiscounts;
    private LocalDateTime date;

}
