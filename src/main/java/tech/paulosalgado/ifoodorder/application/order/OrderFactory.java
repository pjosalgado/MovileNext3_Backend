package tech.paulosalgado.ifoodorder.application.order;

import tech.paulosalgado.ifoodorder.application.customer.CustomerFactory;
import tech.paulosalgado.ifoodorder.application.product.ProductFactory;
import tech.paulosalgado.ifoodorder.domain.customer.exception.CustomerCreationException;
import tech.paulosalgado.ifoodorder.domain.order.Order;
import tech.paulosalgado.ifoodorder.domain.order.PaymentMethod;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.product.Product;
import tech.paulosalgado.ifoodorder.domain.product.exception.ProductCreationException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public abstract class OrderFactory {

    public static Order getOrder(OrderDTO orderDTO) throws OrderCreationException, CustomerCreationException, ProductCreationException {

        List<Product> products = new ArrayList();
        
        for (UUID productId : orderDTO.getProducts()) {
            products.add(ProductFactory.getProduct(productId));
        }

        return new Order.Builder()
                .withCustomer(CustomerFactory.getCustomer(orderDTO.getCustomerId()))
                .withProducts(products)
                .withPaymentMethod(PaymentMethod.get(orderDTO.getPaymentMethod()))
                .withTotalWithDiscounts(orderDTO.getTotalWithDiscounts())
                .withDate(orderDTO.getDate())
                .build();
    }

    public static OrderDTO getDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .customerId(order.getCustomer().getCustomerId())
                .products(order.getProducts().stream()
                        .map(product -> product.getProductId())
                        .collect(Collectors.toList()))
                .paymentMethod(order.getPaymentMethod().name())
                .totalWithDiscounts(order.getTotalWithDiscounts())
                .date(order.getDate())
                .build();
    }

    public static OrderDTO getSimpleDTO(Order order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .build();
    }

}
