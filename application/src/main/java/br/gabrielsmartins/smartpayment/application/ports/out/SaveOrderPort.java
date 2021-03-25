package br.gabrielsmartins.smartpayment.application.ports.out;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import reactor.core.publisher.Mono;

public interface SaveOrderPort {

    Mono<Order> save(Order order);

}
