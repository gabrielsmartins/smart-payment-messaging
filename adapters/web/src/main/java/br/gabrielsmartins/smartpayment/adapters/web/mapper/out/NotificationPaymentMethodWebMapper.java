package br.gabrielsmartins.smartpayment.adapters.web.mapper.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationPaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface NotificationPaymentMethodWebMapper {

    @Mappings({

            @Mapping(target = "paymentType", qualifiedByName = "toType")
    })
    NotificationPaymentMethodDTO mapToDto(PaymentMethod paymentMethod);

    default String toType(PaymentType paymentType){
        return paymentType.getDescription();
    }

}