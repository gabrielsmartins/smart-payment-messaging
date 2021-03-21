package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface PaymentMethodPersistenceMapper {

    @Mappings({
            @Mapping(target = "order", ignore = true),
            @Mapping(target = "paymentType", qualifiedByName = "toType")
    })
    PaymentMethodEntity mapToEntity(PaymentMethod paymentMethod);

    @Mappings({
            @Mapping(target = "order", ignore = true),
            @Mapping(target = "paymentType", qualifiedByName = "toType")
    })@InheritInverseConfiguration
    PaymentMethod mapToDomain(PaymentMethodEntity paymentMethodEntity);

    default String toType(PaymentType paymentType){
        return paymentType.getDescription();
    }

    default PaymentType toType(String description){
        return PaymentType.fromDescription(description);
    }
}