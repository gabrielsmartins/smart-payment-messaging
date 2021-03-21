package br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@Builder(setterPrefix = "with")
@NoArgsConstructor
@AllArgsConstructor
public class OrderLogDTO {

    @JsonProperty("datetime")
    private LocalDateTime datetime;

    @JsonProperty("status")
    private String status;
}
