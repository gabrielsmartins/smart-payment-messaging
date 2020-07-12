package br.gabrielsmartins.smartpayment.application.service.confirmations;

import br.gabrielsmartins.smartpayment.application.domain.confirmations.Confirmation;
import br.gabrielsmartins.smartpayment.application.ports.out.confirmations.SaveConfirmationPort;
import br.gabrielsmartins.smartpayment.application.ports.in.confirmations.SaveConfirmationUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@UseCase
public class SaveConfirmationService implements SaveConfirmationUseCase {

	private final SaveConfirmationPort port;
	
	@Override
	public Confirmation save(Confirmation confirmation) {
		return port.save(confirmation);
	}

}
