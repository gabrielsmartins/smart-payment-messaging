package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodWebMapperTest {

    private PaymentMethodWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PaymentMethodWebMapperImpl();
    }


    @Test
    @DisplayName("Given Payment Method When Map Then Return Payment Method Dto")
    public void givenPaymentMethodWhenMapThenReturnPaymentMethodDto(){

        PaymentMethod paymentMethod = PaymentMethod.builder()
                .withPaymentType(PaymentType.CASH)
                .withAmount(BigDecimal.TEN)
                .build();

        PaymentMethodDTO paymentMethodDTO = this.mapper.mapToDto(paymentMethod);

        assertThat(paymentMethodDTO).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodDTO.getPaymentType()).isEqualTo(paymentMethod.getPaymentType().getDescription());
        assertThat(paymentMethodDTO.getAmount()).isEqualTo(paymentMethod.getAmount());
    }

    @Test
    @DisplayName("Given Payment Method Dto When Map Then Return Payment Method")
    public void givenPaymentMethodDtoWhenMapThenReturnPaymentMethod(){

        PaymentMethodDTO paymentMethodDTO = PaymentMethodDTO.builder()
                .withPaymentType(PaymentType.CASH.getDescription())
                .withAmount(BigDecimal.TEN)
                .build();

        PaymentMethod paymentMethod = this.mapper.mapToDomain(paymentMethodDTO);

        assertThat(paymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(paymentMethod.getPaymentType().getDescription()).isEqualTo(paymentMethodDTO.getPaymentType());
        assertThat(paymentMethod.getAmount()).isEqualTo(paymentMethodDTO.getAmount());
    }
}
