package br.gabrielsmartins.smartpayment.messaging.adapters.persistence;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"br.gabrielsmartins.smartpayment.messaging.adapters.persistence.repository.*"})
public class PersistenceAdapterTest {

    public static void main(String[] args) {
        SpringApplication.run(PersistenceAdapterTest.class, args);
    }
}
