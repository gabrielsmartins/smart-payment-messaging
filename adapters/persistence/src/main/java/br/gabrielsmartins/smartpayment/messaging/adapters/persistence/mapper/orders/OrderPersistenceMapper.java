package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.orders;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.OrderEntity.OrderPaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order.OrderPaymentMethod;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {OrderPersistenceMapper.OrderPaymentMethodPersistenceMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface OrderPersistenceMapper {

    OrderEntity mapToEntity(Order order);

    @InheritInverseConfiguration
    Order mapToDomain(OrderEntity orderEntity);

    @Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface OrderPaymentMethodPersistenceMapper{

        @Mapping(source = "id", target = "paymentMethodId.id")
        OrderPaymentMethodEntity mapToEntity(OrderPaymentMethod paymentMethod);

        @InheritInverseConfiguration
        OrderPaymentMethod mapToDomain(OrderPaymentMethodEntity paymentMethodEntity);

    }

}
