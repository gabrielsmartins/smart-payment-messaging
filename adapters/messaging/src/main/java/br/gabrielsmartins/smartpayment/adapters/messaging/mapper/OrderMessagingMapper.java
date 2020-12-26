package br.gabrielsmartins.smartpayment.adapters.messaging.mapper;

import br.gabrielsmartins.smartpayment.adapters.messaging.message.OrderMessage;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.PaymentType;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMessagingMapper {

    Order mapToDomain(OrderMessage orderMessage);

    default PaymentType toType(String paymentTypeDescription){
        return PaymentType.fromDescription(paymentTypeDescription);
    }


}
