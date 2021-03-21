package br.gabrielsmartins.smartpayment.application.domain.state.observers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderStateObserverFactoryTest {

	private OrderStateObserverFactory factory;
	
	@BeforeEach
	public void setup() {
		this.factory = OrderStateObserverFactory.getInstance();
	}
	
	@Test
	@DisplayName("Given Get Observers Method When Called Then Return Observers List")
	public void givenGetObserversMethodWhenCalledThenReturnObserversList() {
		List<OrderStateObserver> observers = this.factory.getObservers();
		assertThat(observers).isNotNull();
	}
	
	@Test
	@DisplayName("Given Get Instance Method When Called Then Return Same Instance")
	public void givenGetInstanceMethodWhenCalledThenReturnSameInstance() {
		OrderStateObserverFactory instance = OrderStateObserverFactory.getInstance();
		assertThat(instance).isEqualTo(factory);
	}
}
