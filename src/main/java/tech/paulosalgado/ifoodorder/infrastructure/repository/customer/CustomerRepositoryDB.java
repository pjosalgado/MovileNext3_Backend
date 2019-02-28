package tech.paulosalgado.ifoodorder.infrastructure.repository.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.CustomerRepository;

import java.util.UUID;

@Repository
public class CustomerRepositoryDB implements CustomerRepository {

    @Autowired
    private CustomerRepositorySpringData repository;

    public Customer findById(UUID customerId) {
        return repository.findById(customerId).orElse(null);
    }

    public Customer findByCpf(String cpf) {
        return repository.findByCpf(cpf).orElse(null);
    }

    public Customer save(Customer customer) {
        return repository.save(customer);
    }

}
