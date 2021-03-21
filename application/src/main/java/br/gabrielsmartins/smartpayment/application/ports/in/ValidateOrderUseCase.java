package br.gabrielsmartins.smartpayment.application.ports.in;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.common.validation.SelfValidating;
import lombok.Getter;

public interface ValidateOrderUseCase {
	
	Order validate(ValidateOrderCommand command);
	
	@Getter
	class ValidateOrderCommand extends SelfValidating<ValidateOrderCommand> {
		
		@NotNull
		private final String orderId;
		
		@NotNull
		private final Boolean isValid;
		
		@NotNull
		private final LocalDateTime datetime;

		public ValidateOrderCommand(String orderId, Boolean isValid, LocalDateTime datetime) {
			this.orderId = orderId;
			this.isValid = isValid;
			this.datetime = datetime;
			this.validateSelf();
		}
		
		
		
	}

}
