# Example Implementation of a Hexagonal Architecture

This project is an example implementation of a hexagonal architecture using the following technologies:

- JDK 11
- Gradle
- MongoDB
- Spring Boot
- Spring Kafka
- Spring Cloud OpenFeign
- Spring Cloud Sleuth
- Jaeger

### Running Application

1.Run Docker Services:

```console
docker-compose up -d
```

2.Run Application:

```console
./gradlew bootRun --args='--spring.profiles.active=test'
```

OR

```console
./gradlew bootRun --args='--spring.profiles.active=<profile>'
```

### Using Tools

1.For Jaeger go to browser and navigate: http://localhost:16686/

2.For Kafka Control Center UI go to browser and navigate: http://localhost:9021/

3.For MongoDB use MongDCompass with url: 

```console
mongodb://localhost:27017
```


### Other Projects

* [SmartPayment Payments](https://github.com/gabrielsmartins/smartpayment-payments)
* [SmartPayment Account](https://github.com/gabrielsmartins/smartpayment-account)
* [SmartPayment Credit Card](https://github.com/gabrielsmartins/smartpayment-credit-card)