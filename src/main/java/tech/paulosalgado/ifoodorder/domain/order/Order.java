package tech.paulosalgado.ifoodorder.domain.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.paulosalgado.ifoodorder.domain.customer.Customer;
import tech.paulosalgado.ifoodorder.domain.order.exception.OrderCreationException;
import tech.paulosalgado.ifoodorder.domain.product.Product;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "order_request")
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Order {

    @Id
    private UUID id;

    @ManyToOne
    private Customer customer;

    @OneToMany
    private List<Product> products;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private BigDecimal totalWithDiscounts;
    private LocalDateTime date;

    public static class Builder {

        private UUID id;
        private Customer customer;
        private List<Product> products;
        private PaymentMethod paymentMethod;
        private BigDecimal totalWithDiscounts;
        private LocalDateTime date;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Order.Builder withID(UUID id) throws OrderCreationException {

            if (id == null) {
                throw new OrderCreationException("id must be valid");
            }

            this.id = id;
            return this;
        }

        public Order.Builder withCustomer(Customer customer) throws OrderCreationException {

            if (customer == null) {
                throw new OrderCreationException("customer must be valid");
            }

            this.customer = customer;
            return this;
        }

        public Order.Builder withProducts(List<Product> products) throws OrderCreationException {

            if (products == null || products.isEmpty()) {
                throw new OrderCreationException("products must be valid");
            }

            this.products = products;
            return this;
        }

        public Order.Builder withPaymentMethod(PaymentMethod paymentMethod) throws OrderCreationException {

            if (paymentMethod == null) {
                throw new OrderCreationException("paymentMethod must be valid");
            }

            this.paymentMethod = paymentMethod;
            return this;
        }

        public Order.Builder withTotalWithDiscounts(BigDecimal totalWithDiscounts) throws OrderCreationException {

            if (totalWithDiscounts == null || totalWithDiscounts.doubleValue() < 0) {
                throw new OrderCreationException("totalWithDiscounts must be valid");
            }

            this.totalWithDiscounts = totalWithDiscounts;
            return this;
        }

        public Order.Builder withDate(LocalDateTime date) throws OrderCreationException {

            if (date == null) {
                throw new OrderCreationException("date must be valid");
            }

            this.date = date;
            return this;
        }

        public Order build() {
            return new Order(this.id, this.customer, this.products, this.paymentMethod, this.totalWithDiscounts, this.date);
        }

    }

}
