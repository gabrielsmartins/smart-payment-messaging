package br.gabrielsmartins.smartpayment.adapters.persistence.entity.confirmations;

import br.gabrielsmartins.smartpayment.domain.enums.ConfirmationStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;


@ToString
@Getter
@Setter
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
@Document("confirmations")
public class ConfirmationEntity {

    @Id
    @Field("confirmation_id")
    private String id;

    @Field("payment_id")
    private String paymentId;

    @Field("datetime")
    private LocalDateTime dateTime;

    @Field("status")
    private ConfirmationStatus status;


}
