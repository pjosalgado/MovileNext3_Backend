package tech.paulosalgado.ifoodorder.application.customer;

import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;

import java.util.UUID;

public abstract class CustomerFactory {

    public static Customer getCustomer(CustomerDTO customerDTO) throws CustomerCreationException {
        return new Customer.Builder()
                .withName(customerDTO.getName())
                .withCpf(customerDTO.getCpf())
                .build();
    }

    public static Customer getCustomer(UUID customerId) throws CustomerCreationException {
        return new Customer.Builder()
                .withCustomerId(customerId)
                .build();
    }

    public static CustomerDTO getDTO(Customer customer) {
        return CustomerDTO.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .cpf(customer.getCpf())
                .build();
    }

}
