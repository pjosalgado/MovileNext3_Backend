package tech.paulosalgado.ifoodorder.application.order;

import tech.paulosalgado.ifoodorder.application.customer.CustomerFactory;
import tech.paulosalgado.ifoodorder.application.product.ProductDTO;
import tech.paulosalgado.ifoodorder.application.product.ProductFactory;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.order.Order;
import tech.paulosalgado.ifoodorder.domain.order.PaymentMethod;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.util.ArrayList;
import java.util.List;

public abstract class OrderFactory {

    public static Order getCustomer(OrderDTO orderDTO) throws OrderCreationException, CustomerCreationException, ProductCreationException {

        List<Product> products = new ArrayList();
        
        for (ProductDTO productDTO : orderDTO.getProducts()) {
            products.add(ProductFactory.getProduct(productDTO));
        }

        return new Order.Builder()
                .withCustomer(CustomerFactory.getCustomer(orderDTO.getCustomer()))
                .withProducts(products)
                .withPaymentMethod(PaymentMethod.get(orderDTO.getPaymentMethod()))
                .withTotalWithDiscounts(orderDTO.getTotalWithDiscounts())
                .withDate(orderDTO.getDate())
                .build();
    }

//    public static OrderDTO getDTO(Order order) {
//        return OrderDTO.builder()
//                .id(customer.getId())
//                .name(customer.getName())
//                .cpf(customer.getCpf())
//                .build();
//    }

}
