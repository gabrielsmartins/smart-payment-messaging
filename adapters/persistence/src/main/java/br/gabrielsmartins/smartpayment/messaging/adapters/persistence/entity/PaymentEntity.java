package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.strategy.PaymentStrategy;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Entity
@ToString(exclude = {"paymentMethods"})
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;

    @OneToOne
    private OrderEntity order;
    private String paymentNumberIdentifier;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal totalAmount;
    private BigDecimal totalAmountPaid;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "payment")
    private final List<PaymentMethodEntity> paymentMethods = new LinkedList<>();

    public Integer addPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }

    @Entity
    @ToString
    @Getter
    @Setter
    @Builder(setterPrefix = "with")
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PaymentMethodEntity{

        @ManyToOne
        private PaymentEntity payment;
        private BigDecimal discount;
        private BigDecimal totalAmountPaid;
        private PaymentType paymentType;
        private PaymentStrategy paymentStrategy;

    }

}
