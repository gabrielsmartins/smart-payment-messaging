package br.gabrielsmartins.smartpayment.adapters.persistence.mapper.confirmations;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations.ConfirmationEntity;
import br.gabrielsmartins.smartpayment.domain.confirmations.Confirmation;
import org.mapstruct.*;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        unmappedSourcePolicy = ReportingPolicy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        builder = @Builder(disableBuilder = true))
public interface ConfirmationPersistenceMapper {

    ConfirmationEntity mapToEntity(Confirmation confirmation);

    @InheritInverseConfiguration
    Confirmation mapToDomain(ConfirmationEntity confirmationEntity);

}
