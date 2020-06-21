package br.gabrielsmartins.smartpayment.messaging.adapters.messaging.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString(exclude = {"paymentMethods"})
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

    @JsonProperty("payment_methods")
    private final List<OrderPaymentMethodMessage> paymentMethods = new LinkedList<>();


    @Getter
    @Setter
    @ToString
    public static class OrderPaymentMethodMessage{

        @JsonProperty("discount")
        private BigDecimal discount;

        @JsonProperty("total_amount_paid")
        private BigDecimal totalAmountPaid;

        @JsonProperty("payment_type")
        private String paymentType;

    }
}
