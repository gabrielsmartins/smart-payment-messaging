package br.gabrielsmartins.smartpayment.adapters.web.adapter.out;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.OrderRequestDTO;

@FeignClient(name = "orders", url = "${endpoint.orders.url}")
public interface SendOrderWebClient {

	@PostMapping("/orders")
	ResponseEntity<String> submit(@RequestBody OrderRequestDTO request);
	

}
