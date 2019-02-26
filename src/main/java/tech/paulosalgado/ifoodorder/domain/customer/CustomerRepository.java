package tech.paulosalgado.ifoodorder.domain.customer;

import java.util.UUID;

public interface CustomerRepository {

    Customer findById(UUID id);
    Customer findByCpf(String cpf);

    Customer save(Customer customer);

}
