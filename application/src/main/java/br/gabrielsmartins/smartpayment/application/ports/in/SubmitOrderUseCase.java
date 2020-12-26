package br.gabrielsmartins.smartpayment.application.ports.in;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.common.validation.SelfValidating;
import lombok.Getter;

public interface SubmitOrderUseCase {
	
	Order submit(SubmitOrderCommand command);
	
	@Getter
	class SubmitOrderCommand extends SelfValidating<SubmitOrderCommand>{
		
		@Valid
		@NotNull
		private final Order order;

		public SubmitOrderCommand(Order order) {
			this.order = order;
			this.validateSelf();
		}
		
	}

}
