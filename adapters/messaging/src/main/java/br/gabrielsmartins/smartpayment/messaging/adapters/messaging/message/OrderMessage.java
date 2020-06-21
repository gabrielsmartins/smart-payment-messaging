package br.gabrielsmartins.smartpayment.messaging.adapters.messaging.message;

import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class OrderMessage {

    @JsonProperty("id")
    private String id;

    @JsonProperty("payment_number_identifier")
    private String paymentNumberIdentifier;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("discount")
    private BigDecimal discount;

    @JsonProperty("total_amount_paid")
    private BigDecimal totalAmountPaid;

    @JsonProperty("payment_type")
    private String paymentType;
}
