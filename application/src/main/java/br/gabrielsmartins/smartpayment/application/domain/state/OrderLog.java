package br.gabrielsmartins.smartpayment.application.domain.state;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString(exclude = {"order"})
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLog {

    private Order order;
    private LocalDateTime datetime;
    private OrderStatus status;
}
