package br.gabrielsmartins.smartpayment.adapters.web.mapper.in;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderLogDTO;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderLogWebMapperTest {

    private OrderLogWebMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderLogWebMapperImpl();
    }

    @Test
    @DisplayName("Given Order Log When Map Then Return Order Log Dto")
    public void givenOrderLogWhenMapThenReturnOrderLogDto(){

        OrderLog orderLog = OrderLog.builder()
                .withStatus(OrderStatus.COMPLETED)
                .withDatetime(LocalDateTime.now())
                .build();

        OrderLogDTO orderLogDTO = this.mapper.mapToDto(orderLog);

        assertThat(orderLogDTO).hasNoNullFieldsOrProperties();
        assertThat(orderLogDTO.getDatetime()).isEqualTo(orderLog.getDatetime());
        assertThat(orderLogDTO.getStatus()).isEqualTo(orderLog.getStatus().getDescription());
    }

    @Test
    @DisplayName("Given Order Log Dto When Map Then Return Order Log")
    public void givenOrderLogDtoWhenMapThenReturnOrderLog(){

        OrderLogDTO orderLogDTO = OrderLogDTO.builder()
                .withStatus(OrderStatus.COMPLETED.getDescription())
                .withDatetime(LocalDateTime.now())
                .build();

        OrderLog orderLog = this.mapper.mapToDomain(orderLogDTO);

        assertThat(orderLog).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderLog.getDatetime()).isEqualTo(orderLogDTO.getDatetime());
        assertThat(orderLog.getStatus().getDescription()).isEqualTo(orderLogDTO.getStatus());
    }

}
