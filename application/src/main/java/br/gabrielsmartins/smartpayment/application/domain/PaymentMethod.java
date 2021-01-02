package br.gabrielsmartins.smartpayment.application.domain;


import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString(exclude = {"order"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class PaymentMethod {

    private Order order;
    private PaymentType paymentType;
    private BigDecimal amount;

}
