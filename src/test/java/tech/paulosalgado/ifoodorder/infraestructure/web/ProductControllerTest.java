package tech.paulosalgado.ifoodorder.infraestructure.web;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import tech.paulosalgado.ifoodorder.AbstractTest;
import tech.paulosalgado.ifoodorder.application.customer.CustomerDTO;
import tech.paulosalgado.ifoodorder.application.product.ProductDTO;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.ProductRepository;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest extends AbstractTest {

    @Autowired
    private ProductRepository repository;

    private Product product;

    @Before
    public void setup() throws ProductCreationException {

        super.setupMock();

        product = repository.save(getProduct());
    }

    @Test
    public void shouldSaveProduct() throws Exception {

        super.mockMvc.perform(post("/products")
                .contentType(MediaType.parseMediaType("application/json"))
                .content(super.GSON.toJson(ProductDTO.builder()
                        .name("Pizza Quatro Queijos")
                        .price(BigDecimal.valueOf(49.90))
                        .build())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("productId").isNotEmpty())
                .andExpect(jsonPath("name").value("Pizza Quatro Queijos"))
                .andExpect(jsonPath("price").value(BigDecimal.valueOf(49.90)))
                .andDo(print());
    }

    @Test
    public void shouldFailSavingProductWithNoPrice() throws Exception {

        super.mockMvc.perform(post("/products")
                .contentType(MediaType.parseMediaType("application/json"))
                .content(super.GSON.toJson(CustomerDTO.builder()
                        .name("Pizza Quatro Queijos")
                        .build())))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("type").value("invalid_input"))
                .andExpect(jsonPath("message").value("price must be valid"))
                .andDo(print());
    }

    @Test
    public void shouldFindProduct() throws Exception {

        super.mockMvc.perform(get("/products/" + product.getProductId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("productId").isNotEmpty())
                .andExpect(jsonPath("productId").value(product.getProductId().toString()))
                .andExpect(jsonPath("name").value(product.getName()))
                .andExpect(jsonPath("price").value(product.getPrice()))
                .andDo(print());
    }

    @Test
    public void shouldFindAllProducts() throws Exception {

        super.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists())
                .andExpect(jsonPath("$[0].productId").value(product.getProductId().toString()))
                .andExpect(jsonPath("$[0].name").value(product.getName()))
                .andExpect(jsonPath("$[0].price").value(product.getPrice()))
                .andExpect(jsonPath("$[1]").doesNotExist())
                .andDo(print());
    }

    @Test
    public void shouldFailTryFindingNotExistingProduct() throws Exception {

        super.mockMvc.perform(get("/products/5413baf4-91df-488e-85d5-2e013ffdbdf9"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("type").value("not_found"))
                .andExpect(jsonPath("message").value("product not found: 5413baf4-91df-488e-85d5-2e013ffdbdf9"))
                .andDo(print());
    }

    @After
    public void tearDown() {
        repository.deleteAll();
    }

}
