package tech.paulosalgado.ifoodorder.infraestructure.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import tech.paulosalgado.ifoodorder.AbstractTest;
import tech.paulosalgado.ifoodorder.application.order.OrderDTO;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.CustomerRepository;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.order.Order;
import tech.paulosalgado.ifoodorder.domain.order.OrderRepository;
import tech.paulosalgado.ifoodorder.domain.order.PaymentMethod;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.ProductRepository;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends AbstractTest {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    private Customer customer;
    private Product product;
    private Order order;

    @Before
    public void setup() throws OrderCreationException, CustomerCreationException, ProductCreationException {

        super.setupMock();

        customer = customerRepository.save(super.getCustomer());
        product = productRepository.save(super.getProduct());
        order = repository.save(super.getOrder(customer, Arrays.asList(product)));
    }

    @Test
    @Ignore
    public void shouldSaveOrder() throws Exception {

        super.mockMvc.perform(post("/orders")
                .contentType(MediaType.parseMediaType("application/json"))
                .content(super.GSON.toJson(OrderDTO.builder()
                        .customerId(customer.getCustomerId())
                        .products(Arrays.asList(
                                product.getProductId()))
                        .date(LocalDateTime.now())
                        .paymentMethod(PaymentMethod.MONEY.name())
                        .totalWithDiscounts(BigDecimal.valueOf(45.99))
                        .build())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("orderId").isNotEmpty())
                .andDo(print());
    }

    // TODO: test fail save order, after 'verify if customer and products exists' in 'OrderServiceImpl'

    @Test
    public void shouldFindOrder() throws Exception {

        super.mockMvc.perform(get("/orders/" + order.getOrderId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("orderId").isNotEmpty())
                .andExpect(jsonPath("orderId").value(order.getOrderId().toString()))
                .andExpect(jsonPath("customerId").value(order.getCustomer().getCustomerId().toString()))
                .andExpect(jsonPath("products").isArray())
                .andExpect(jsonPath("products[0]").value(order.getProducts().get(0).getProductId().toString()))
                .andExpect(jsonPath("products[1]").doesNotExist())
                .andExpect(jsonPath("date").value(order.getDate().toString()))
                .andExpect(jsonPath("paymentMethod").value(order.getPaymentMethod().name()))
                .andExpect(jsonPath("totalWithDiscounts").value(order.getTotalWithDiscounts()))
                .andDo(print());
    }

    @Test
    public void shouldFailTryFindingNotExistingOrder() throws Exception {

        super.mockMvc.perform(get("/orders/5413baf4-91df-488e-85d5-2e013ffdbdf9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("type").value("not_found"))
                .andExpect(jsonPath("message").value("order not found: 5413baf4-91df-488e-85d5-2e013ffdbdf9"))
                .andDo(print());
    }

    @After
    public void tearDown() {
        repository.deleteAll();
        productRepository.deleteAll();
        customerRepository.deleteAll();
    }

}
