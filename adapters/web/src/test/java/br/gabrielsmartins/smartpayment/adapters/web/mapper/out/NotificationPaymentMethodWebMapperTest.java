package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationPaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
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
        PaymentMethod paymentMethod = defaultPaymentMethod().build();

        NotificationPaymentMethodDTO paymentMethodDTO = this.mapper.mapToDto(paymentMethod);

        assertThat(paymentMethodDTO).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodDTO.getPaymentType()).isEqualTo(paymentMethod.getPaymentType().getDescription());
        assertThat(paymentMethodDTO.getAmount()).isEqualTo(paymentMethod.getAmount());
    }
}
