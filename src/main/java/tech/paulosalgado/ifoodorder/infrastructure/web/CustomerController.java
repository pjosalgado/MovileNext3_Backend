package tech.paulosalgado.ifoodorder.infrastructure.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.application.customer.CustomerDTO;
import tech.paulosalgado.ifoodorder.application.customer.CustomerFactory;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.CustomerService;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerNotFoundException;

import java.util.UUID;

@RestController
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customers/{id}")
    public CustomerDTO getCustomer(@PathVariable("id") UUID id) throws CustomerNotFoundException {

        Customer customer = service.findById(id);

        return CustomerFactory.getDTO(customer);
    }

    @PostMapping("/customers")
    public CustomerDTO postCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerCreationException {

        Customer customer = CustomerFactory.getCustomer(customerDTO);
        customer = service.save(customer);

        return CustomerFactory.getDTO(customer);
    }

}
