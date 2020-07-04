package br.gabrielsmartins.smartpayment.messaging.adapters.persistence.mapper.payments;

import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity;
import br.gabrielsmartins.smartpayment.messaging.adapters.persistence.entity.payments.PaymentEntity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment;
import br.gabrielsmartins.smartpayment.messaging.application.domain.payments.Payment.PaymentMethod;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {PaymentPersistenceMapper.PaymentMethodPersistenceMapper.class},
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface PaymentPersistenceMapper {

    PaymentEntity mapToEntity(Payment payment);

    @InheritInverseConfiguration
    Payment mapToDomain(PaymentEntity paymentEntity);

    @Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
            unmappedSourcePolicy = ReportingPolicy.IGNORE,
            unmappedTargetPolicy = ReportingPolicy.IGNORE)
    interface PaymentMethodPersistenceMapper{

        PaymentMethodEntity mapToEntity(PaymentMethod paymentMethod);

        @InheritInverseConfiguration
        PaymentMethod mapToDomain(PaymentMethodEntity paymentMethodEntity);

    }

}
