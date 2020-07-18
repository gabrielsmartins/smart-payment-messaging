package br.gabrielsmartins.smartpayment.application.ports.in.confirmations;

import br.gabrielsmartins.smartpayment.application.domain.confirmations.Confirmation;

public interface SaveConfirmationUseCase {

    Confirmation save(Confirmation confirmation);
}
