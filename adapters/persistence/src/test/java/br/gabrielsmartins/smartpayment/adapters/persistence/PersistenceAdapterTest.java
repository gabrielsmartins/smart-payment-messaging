package br.gabrielsmartins.smartpayment.adapters.persistence;

import br.gabrielsmartins.smartpayment.adapters.persistence.repository.confirmations.ConfirmationRepository;
import br.gabrielsmartins.smartpayment.adapters.persistence.repository.orders.OrderRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = {OrderRepository.class, ConfirmationRepository.class})
public class PersistenceAdapterTest {

    public static void main(String[] args) {
        SpringApplication.run(PersistenceAdapterTest.class, args);
    }
}
