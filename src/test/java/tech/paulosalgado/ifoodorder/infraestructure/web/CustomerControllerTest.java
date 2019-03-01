package tech.paulosalgado.ifoodorder.infraestructure.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import tech.paulosalgado.ifoodorder.AbstractTest;
import tech.paulosalgado.ifoodorder.application.customer.CustomerDTO;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.CustomerRepository;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends AbstractTest {

    @Autowired
    private CustomerRepository repository;

    private Customer customer;

    @Before
    public void setup() throws CustomerCreationException {

        super.setupMock();

        customer = repository.save(getCustomer());
    }

    @Test
    public void shouldSaveCustomer() throws Exception {

        super.mockMvc.perform(post("/customers")
                .contentType(MediaType.parseMediaType("application/json"))
                .content(super.GSON.toJson(CustomerDTO.builder()
                        .name("Ricardo Pereira")
                        .cpf("37661172038")
                        .build())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("customerId").isNotEmpty())
                .andExpect(jsonPath("name").value("Ricardo Pereira"))
                .andExpect(jsonPath("cpf").value("37661172038"))
                .andDo(print());
    }

    @Test
    public void shouldFailSavingCustomerWithNoCpf() throws Exception {

        super.mockMvc.perform(post("/customers")
                .contentType(MediaType.parseMediaType("application/json"))
                .content(super.GSON.toJson(CustomerDTO.builder()
                        .name("Ricardo Pereira")
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("type").value("invalid_input"))
                .andExpect(jsonPath("message").value("cpf must be valid"))
                .andDo(print());
    }

    @Test
    public void shouldFailTrySavingCustomerWithSameCpfAlreadyExisting() throws Exception {

        super.mockMvc.perform(post("/customers")
                .contentType(MediaType.parseMediaType("application/json"))
                .content(super.GSON.toJson(CustomerDTO.builder()
                        .name("Ricardo Pereira")
                        .cpf("57642963076")
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("type").value("invalid_input"))
                .andExpect(jsonPath("message").value("customer already exists"))
                .andDo(print());
    }

    @Test
    public void shouldFindCustomer() throws Exception {

        super.mockMvc.perform(get("/customers/" + customer.getCustomerId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("customerId").isNotEmpty())
                .andExpect(jsonPath("customerId").value(customer.getCustomerId().toString()))
                .andExpect(jsonPath("name").value(customer.getName()))
                .andExpect(jsonPath("cpf").value(customer.getCpf()))
                .andDo(print());
    }

    @Test
    public void shouldFailTryFindingNotExistingCustomer() throws Exception {

        super.mockMvc.perform(get("/customers/5413baf4-91df-488e-85d5-2e013ffdbdf9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("type").value("not_found"))
                .andExpect(jsonPath("message").value("customer not found: 5413baf4-91df-488e-85d5-2e013ffdbdf9"))
                .andDo(print());
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

}
