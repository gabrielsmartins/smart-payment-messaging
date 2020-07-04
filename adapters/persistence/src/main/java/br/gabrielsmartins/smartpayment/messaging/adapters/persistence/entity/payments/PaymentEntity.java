package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.orders.OrderEntity;
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
@Document("payments")
public class PaymentEntity {

    @Id
    @Field("payment_id")
    private String id;
    @Field("order_id")
    private OrderEntity order;
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
    private final List<PaymentMethodEntity> paymentMethods = new LinkedList<>();

    public Integer addPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }

    @ToString
    @Getter
    @Setter
    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentMethodEntity{

        @Field("payment_method_id")
        private Long id;
        @Field("payment_id")
        private PaymentEntity payment;
        @Field("discount")
        private BigDecimal discount;
        @Field("total_amount_paid")
        private BigDecimal totalAmountPaid;
        @Field("payment_type")
        private PaymentType paymentType;

    }


}
