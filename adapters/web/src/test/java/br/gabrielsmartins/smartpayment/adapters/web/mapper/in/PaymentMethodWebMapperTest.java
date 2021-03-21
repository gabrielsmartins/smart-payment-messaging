package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.adapters.web.support.PaymentMethodDTOSupport.defaultPaymentMethodDto;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
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

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

        PaymentMethodDTO paymentMethodDTO = this.mapper.mapToDto(paymentMethod);

        assertThat(paymentMethodDTO).hasNoNullFieldsOrProperties();
        assertThat(paymentMethodDTO.getPaymentType()).isEqualTo(paymentMethod.getPaymentType().getDescription());
        assertThat(paymentMethodDTO.getAmount()).isEqualTo(paymentMethod.getAmount());
    }

    @Test
    @DisplayName("Given Payment Method Dto When Map Then Return Payment Method")
    public void givenPaymentMethodDtoWhenMapThenReturnPaymentMethod(){

        PaymentMethodDTO paymentMethodDTO = defaultPaymentMethodDto().build();

        PaymentMethod paymentMethod = this.mapper.mapToDomain(paymentMethodDTO);

        assertThat(paymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(paymentMethod.getPaymentType().getDescription()).isEqualTo(paymentMethodDTO.getPaymentType());
        assertThat(paymentMethod.getAmount()).isEqualTo(paymentMethodDTO.getAmount());
    }
}
