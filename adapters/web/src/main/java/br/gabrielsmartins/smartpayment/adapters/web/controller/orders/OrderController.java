package br.gabrielsmartins.smartpayment.adapters.web.controller.orders;

import br.gabrielsmartins.smartpayment.adapters.web.dto.orders.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.orders.OrderWebMapper;
import br.gabrielsmartins.smartpayment.domain.orders.Order;
import br.gabrielsmartins.smartpayment.ports.in.orders.SaveOrderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final SaveOrderUseCase saveOrderUseCase;
    private final OrderWebMapper mapper;

    @PostMapping
    public ResponseEntity<?> save(@RequestHeader HttpHeaders httpHeaders, @RequestBody OrderDTO orderDTO){
        Order order = mapper.mapToDomain(orderDTO);
        Order savedOrder = saveOrderUseCase.save(order);
        return new ResponseEntity<>(mapper.mapToDto(savedOrder), HttpStatus.ACCEPTED);
    }

}
