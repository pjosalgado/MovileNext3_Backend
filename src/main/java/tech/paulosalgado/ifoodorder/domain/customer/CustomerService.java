package tech.paulosalgado.ifoodorder.domain.customer;

import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerNotFoundException;

import java.util.UUID;

public interface CustomerService {

    Customer findById(UUID id) throws CustomerNotFoundException;

    Customer save(Customer customer) throws CustomerCreationException;

}
