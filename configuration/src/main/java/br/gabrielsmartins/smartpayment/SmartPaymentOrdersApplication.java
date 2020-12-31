package br.gabrielsmartins.smartpayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SmartPaymentOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartPaymentOrdersApplication.class, args);
	}

}
