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

    private BigDecimal total;
    private LocalDateTime date;

    public static class Builder {

        private UUID id;
        private Customer customer;
        private List<Product> products;
        private PaymentMethod paymentMethod;
        private BigDecimal total;
        private LocalDateTime date;

        public Builder() {
            this.id = UUID.randomUUID();
        }

        public Order.Builder withCustomer(Customer customer) throws OrderCreationException {

            if (customer == null) {
                throw new OrderCreationException("customer must be valid");
            }

            this.customer = customer;
            return this;
        }

        // TODO products

        // TODO paymentMethod

        // TODO total

        // TODO date

        public Order build() {
            return new Order(this.id, this.customer, this.products, this.paymentMethod, this.total, this.date);
        }

    }

}
