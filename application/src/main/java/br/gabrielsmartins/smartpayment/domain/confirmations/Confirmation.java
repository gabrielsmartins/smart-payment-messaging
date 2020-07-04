package br.gabrielsmartins.smartpayment.domain.confirmations;

import br.gabrielsmartins.smartpayment.domain.enums.ConfirmationStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(setterPrefix = "with")
@ToString
public class Confirmation {

    private String id;
    private String paymentId;
    private LocalDateTime dateTime;
    private ConfirmationStatus status;

}
