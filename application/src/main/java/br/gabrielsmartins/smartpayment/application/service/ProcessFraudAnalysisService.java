package br.gabrielsmartins.smartpayment.application.service;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.ProcessFraudAnalysisUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SearchOrderUseCase;
import br.gabrielsmartins.smartpayment.common.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@UseCase
@RequiredArgsConstructor
@Slf4j
public class ProcessFraudAnalysisService implements ProcessFraudAnalysisUseCase {

    private final SearchOrderUseCase searchOrderUseCase;

    @Override
    public Mono<Order> process(Long orderId, boolean isFraud) {
        log.info("Search for order: {}", orderId);
        return searchOrderUseCase.findById(orderId)
                                 .flatMap(order -> updateOrder(order, isFraud));
    }

    private Mono<Order> updateOrder(Order order, boolean isFraud) {
        log.info("Updating order: {}, fraud: {}", order, isFraud);
        if (isFraud)
            return Mono.just(order.next().getOrder());
        return Mono.just(order.reject().getOrder());
    }

}
