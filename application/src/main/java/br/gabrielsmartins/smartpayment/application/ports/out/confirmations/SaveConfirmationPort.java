package br.gabrielsmartins.smartpayment.application.ports.out.confirmations;

import br.gabrielsmartins.smartpayment.application.domain.confirmations.Confirmation;

public interface SaveConfirmationPort {
    Confirmation save(Confirmation confirmation);
}
