package br.gabrielsmartins.smartpayment.application.ports.out.orders;

import br.gabrielsmartins.smartpayment.application.domain.orders.Order;

public interface SaveOrderPort {
    Order save(Order order);
}
