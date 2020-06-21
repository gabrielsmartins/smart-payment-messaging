package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.PaymentEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.PaymentEntity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment.PaymentMethod;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PaymentPersistenceMapper.PaymentMethodPersistenceMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentPersistenceMapper {

    PaymentEntity mapToEntity(Payment payment);

    Payment mapToDomain(PaymentEntity paymentEntity);

    @Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface PaymentMethodPersistenceMapper{

        PaymentMethodEntity mapToEntity(PaymentMethod paymentMethod);

        PaymentMethod mapToDomain(PaymentMethodEntity paymentMethodEntity);

    }

}
