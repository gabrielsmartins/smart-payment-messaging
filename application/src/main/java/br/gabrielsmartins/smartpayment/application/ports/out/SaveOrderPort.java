package br.gabrielsmartins.smartpayment.application.ports.out;

import br.gabrielsmartins.smartpayment.application.domain.Order;

public interface SaveOrderPort {
    Order save(Order order);
}
