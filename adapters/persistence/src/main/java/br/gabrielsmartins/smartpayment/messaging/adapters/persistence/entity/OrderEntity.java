package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
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
public class OrderEntity {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String paymentNumberIdentifier;
    private LocalDate dueDate;
    private LocalDate paymentDate;
    private BigDecimal totalAmount;
    private BigDecimal totalAmountPaid;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "order")
    private final List<OrderPaymentMethodEntity> paymentMethods = new LinkedList<>();

    public Integer addPaymentMethod(OrderPaymentMethodEntity paymentMethod) {
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
    public static class OrderPaymentMethodEntity{

        @EmbeddedId
        private OrderPaymentMethodEntityId paymentMethodId;
        private BigDecimal discount;
        private BigDecimal totalAmountPaid;
        private PaymentType paymentType;

        @Entity
        @ToString
        @Getter
        @Setter
        @Builder(setterPrefix = "with")
        @AllArgsConstructor
        @NoArgsConstructor
        @Embeddable
        public static class OrderPaymentMethodEntityId{
            private Long id;
            @ManyToOne(targetEntity = OrderEntity.class)
            private OrderEntity order;
        }

    }

}
