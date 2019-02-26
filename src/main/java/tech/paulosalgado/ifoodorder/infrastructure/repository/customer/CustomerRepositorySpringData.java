package tech.paulosalgado.ifoodorder.infrastructure.repository.customer;

import org.springframework.data.repository.CrudRepository;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepositorySpringData extends CrudRepository<Customer, UUID> {

    Optional<Customer> findByCpf(String cpf);

}
