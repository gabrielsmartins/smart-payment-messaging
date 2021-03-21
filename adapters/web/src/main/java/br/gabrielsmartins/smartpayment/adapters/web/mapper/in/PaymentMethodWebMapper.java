package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.PaymentMethodDTO;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface PaymentMethodWebMapper {

    @Mappings({

            @Mapping(target = "paymentType", qualifiedByName = "toType")
    })
    PaymentMethodDTO mapToDto(PaymentMethod paymentMethod);

    @Mappings({
            @Mapping(target = "order", ignore = true),
            @Mapping(target = "paymentType", qualifiedByName = "toType")
    })
    @InheritInverseConfiguration
    PaymentMethod mapToDomain(PaymentMethodDTO paymentMethodDTO);

    default String toType(PaymentType paymentType){
        return paymentType.getDescription();
    }

    default PaymentType toType(String description){
        return PaymentType.fromDescription(description);
    }
}