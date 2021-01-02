package br.gabrielsmartins.smartpayment.adapters.web.adapter.out;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.out.dto.NotificationOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications", url = "${endpoint.notifications.url}")
public interface NotificationOrderWebClient {

	@PostMapping("/notifications")
	ResponseEntity<String> submit(@RequestBody NotificationOrderDTO request);
	

}
