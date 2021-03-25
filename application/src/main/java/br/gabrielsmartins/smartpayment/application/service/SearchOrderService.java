package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SearchOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.out.SearchOrderPort;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
public class SearchOrderService implements SearchOrderUseCase {

    private final SearchOrderPort port;

    @Override
    public Mono<Order> findById(Long id) {
        return port.findById(id);
    }

}
