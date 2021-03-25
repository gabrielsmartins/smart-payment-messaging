package br.gabrielsmartins.smartpayment.adapters.web.adapter.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase.SubmitOrderCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class SubmitOrderController {

    private final SubmitOrderUseCase useCase;
    private final OrderWebMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Mono<OrderDTO> save(@RequestHeader HttpHeaders httpHeaders, @RequestBody OrderDTO orderDTO){
        Order order = mapper.mapToDomain(orderDTO);
        var command = new SubmitOrderCommand(order);
        return useCase.submit(command)
                      .flatMap(submittedOrder -> Mono.just(mapper.mapToDto(submittedOrder)));
    }

}
