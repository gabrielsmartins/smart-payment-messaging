package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;


@ToString(exclude = {"paymentMethods"})
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Document("orders")
public class OrderEntity {

    @Id
    @Field("order_id")
    private String id;
    @Field("payment_number_identifier")
    private String paymentNumberIdentifier;
    @Field("due_date")
    private LocalDate dueDate;
    @Field("payment_date")
    private LocalDate paymentDate;
    @Field("total_amount")
    private BigDecimal totalAmount;
    @Field("total_amount_paid")
    private BigDecimal totalAmountPaid;
    @Field("payment_methods")
    private final List<OrderPaymentMethodEntity> paymentMethods = new LinkedList<>();

    public Integer addPaymentMethod(OrderPaymentMethodEntity paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }


    @ToString
    @Getter
    @Setter
    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OrderPaymentMethodEntity{

        @Field("payment_method_id")
        private Long id;
        @Field("order_id")
        private OrderEntity order;
        @Field("discount")
        private BigDecimal discount;
        @Field("total_amount_paid")
        private BigDecimal totalAmountPaid;
        @Field("payment_type")
        private PaymentType paymentType;

    }

}
