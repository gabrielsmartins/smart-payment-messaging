package br.gabrielsmartins.smartpayment.adapters.messaging.mapper.in;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMessagingMapper {

    Order mapToDomain(Object orderMessage);

    default PaymentType toType(String paymentTypeDescription){
        return PaymentType.fromDescription(paymentTypeDescription);
    }


}
