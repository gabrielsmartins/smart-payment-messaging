package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.orders.mapper;

import br.gabrielsmartins.schemas.order_requested.PaymentMethod;
import br.gabrielsmartins.schemas.order_requested.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PaymentMethodMessagingConsumerMapperTest {

    private PaymentMethodMessagingConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PaymentMethodMessagingConsumerMapper();
    }

    @Test
    @DisplayName("Given Message When Map Then Return Payment Method")
    public void givenMessageWhenMapThenReturnPaymentMethod(){
        var message = PaymentMethod.newBuilder()
                                                .setAmount(BigDecimal.valueOf(1500))
                                                .setPaymentType(PaymentType.CASH)
                                                .build();

        br.gabrielsmartins.smartpayment.application.domain.PaymentMethod paymentMethod = this.mapper.mapToDomain(message);

        assertThat(paymentMethod).isNotNull();
        assertThat(paymentMethod.getAmount()).isEqualTo(message.getAmount());
        assertThat(paymentMethod.getPaymentType().getDescription()).isEqualTo(message.getPaymentType().name());
    }
}
