package br.gabrielsmartins.smartpayment.service.confirmations;

import br.gabrielsmartins.smartpayment.domain.confirmations.Confirmation;
import br.gabrielsmartins.smartpayment.ports.out.confirmations.SaveConfirmationPort;
import br.gabrielsmartins.smartpayment.ports.in.confirmations.SaveConfirmationUseCase;
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
