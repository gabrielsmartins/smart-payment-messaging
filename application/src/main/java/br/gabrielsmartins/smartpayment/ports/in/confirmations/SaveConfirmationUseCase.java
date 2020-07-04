package br.gabrielsmartins.smartpayment.ports.in.confirmations;

import br.gabrielsmartins.smartpayment.domain.confirmations.Confirmation;

public interface SaveConfirmationUseCase {

    Confirmation save(Confirmation confirmation);
}
