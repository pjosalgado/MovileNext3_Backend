package tech.paulosalgado.ifoodorder.application.customer;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class CustomerDTO {

    private UUID customerId;
    private String name;
    private String cpf;

}
