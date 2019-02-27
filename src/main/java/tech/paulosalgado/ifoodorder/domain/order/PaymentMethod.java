package tech.paulosalgado.ifoodorder.domain.order;

import java.util.stream.Stream;

public enum PaymentMethod {

    CREDIT_CARD,
    DEBIT_CARD,
    VOUCHER,
    MONEY;

    public static PaymentMethod get(String methodToFind) {
        return Stream.of(values())
                .filter(paymentMethod -> paymentMethod.toString().toUpperCase().equals(methodToFind.toUpperCase()))
                .findFirst().orElse(null);
    }

}
