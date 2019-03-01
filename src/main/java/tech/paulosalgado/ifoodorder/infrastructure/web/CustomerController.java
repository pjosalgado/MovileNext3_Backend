package tech.paulosalgado.ifoodorder.infrastructure.web;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.paulosalgado.ifoodorder.application.customer.CustomerDTO;
import tech.paulosalgado.ifoodorder.application.customer.CustomerFactory;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.CustomerService;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerNotFoundException;

import java.util.UUID;

@RestController
@Api(tags = "Customer")
public class CustomerController {

    @Autowired
    private CustomerService service;

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable("customerId") UUID customerId) throws CustomerNotFoundException {

        Customer customer = service.findById(customerId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CustomerFactory.getDTO(customer));
    }

    @PostMapping("/customers")
    public ResponseEntity<CustomerDTO> postCustomer(@RequestBody CustomerDTO customerDTO) throws CustomerCreationException {

        Customer customer = CustomerFactory.getCustomer(customerDTO);
        customer = service.save(customer);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CustomerFactory.getDTO(customer));
    }

}
