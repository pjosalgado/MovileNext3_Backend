package tech.paulosalgado.ifoodorder.domain.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerNotFoundException;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Customer findById(UUID id) throws CustomerNotFoundException {

        Customer customer = repository.findById(id);

        if (customer == null) {
            throw new CustomerNotFoundException(id);
        }

        return customer;
    }

    public Customer save(Customer customer) throws CustomerCreationException {

        Customer existingCustomer = repository.findByCpf(customer.getCpf());

        if (existingCustomer != null) {
            throw new CustomerCreationException("customer already exists");
        }

        return repository.save(customer);
    }

}
