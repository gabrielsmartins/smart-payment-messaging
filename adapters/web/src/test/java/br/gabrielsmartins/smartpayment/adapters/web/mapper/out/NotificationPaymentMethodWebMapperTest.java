package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationPaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class NotificationPaymentMethodWebMapperTest {

    private NotificationPaymentMethodWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new NotificationPaymentMethodWebMapperImpl();
    }

    @Test
    @DisplayName("Given Payment Method When Map Then Return Notification Payment Method Dto")
    public void givenPaymentMethodWhenMapThenReturnNotificationPaymentMethodDto(){
        PaymentMethod paymentMethod = PaymentMethod.builder()
                                                    .withPaymentType(PaymentType.CASH)
                                                    .withAmount(BigDecimal.TEN)
                                                    .build();

        NotificationPaymentMethodDTO paymentMethodDTO = this.mapper.mapToDto(paymentMethod);

        assertThat(paymentMethodDTO).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodDTO.getPaymentType()).isEqualTo(paymentMethod.getPaymentType().getDescription());
        assertThat(paymentMethodDTO.getAmount()).isEqualTo(paymentMethod.getAmount());
    }
}
