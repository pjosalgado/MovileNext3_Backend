package tech.paulosalgado.ifoodorder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.order.Order;
import tech.paulosalgado.ifoodorder.domain.order.PaymentMethod;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractTest {

    protected MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    protected final Gson GSON = new GsonBuilder().create();

    public void setupMock() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    public static Customer getCustomer() throws CustomerCreationException {
        return new Customer.Builder()
                .withName("Marcos Souza")
                .withCpf("57642963076")
                .build();
    }

    public static Product getProduct() throws ProductCreationException {
        return new Product.Builder()
                .withName("Pizza Dois Queijos")
                .withPrice(BigDecimal.valueOf(39.90))
                .build();
    }

    public static Order getOrder(Customer customer, List<Product> products) throws OrderCreationException {
        return new Order.Builder()
                .withCustomer(customer)
                .withProducts(products)
                .withDate(LocalDateTime.now())
                .withPaymentMethod(PaymentMethod.CREDIT_CARD)
                .withTotalWithDiscounts(BigDecimal.valueOf(50.00))
                .build();
    }

}
