package br.gabrielsmartins.smartpayment.adapters.web.dto.orders;

import br.gabrielsmartins.smartpayment.adapters.web.enums.PaymentTypeDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDTO {

    @JsonProperty(value = "id", access = JsonProperty.Access.WRITE_ONLY)
    private String id;

    @JsonProperty("payment_number_identifier")
    private String paymentNumberIdentifier;

    @JsonProperty("due_date")
    private LocalDate dueDate;

    @JsonProperty("payment_date")
    private LocalDate paymentDate;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

    @JsonProperty("total_amount_paid")
    private BigDecimal totalAmountPaid;

    @JsonProperty("payment_methods")
    private final List<OrderPaymentMethodDTO> paymentMethods = new LinkedList<>();

    public Integer addPaymentMethod(OrderPaymentMethodDTO paymentMethod) {
        this.paymentMethods.add(paymentMethod);
        return this.paymentMethods.size();
    }

    @Getter
    @Setter
    @ToString
    @Builder(setterPrefix = "with")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class OrderPaymentMethodDTO {

        @JsonProperty("payment_method_id")
        private Long id;

        @JsonProperty("discount")
        private BigDecimal discount;

        @JsonProperty("total_amount_paid")
        private BigDecimal totalAmountPaid;

        @JsonProperty("payment_type")
        private PaymentTypeDTO paymentType;
    }
}
