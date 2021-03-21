package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.OrderLogEntity;
import br.gabrielsmartins.smartpayment.application.domain.state.OrderLog;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.OrderLogEntitySupport.defaultOrderLogEntity;
import static br.gabrielsmartins.smartpayment.application.support.OrderLogSupport.defaultOrderLog;
import static org.assertj.core.api.Assertions.assertThat;

public class OrderLogPersistenceMapperTest {

    private OrderLogPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderLogPersistenceMapperImpl();
    }

    @Test
    @DisplayName("Given Order Log When Map Then Return Order Log Entity")
    public void givenOrderLogWhenMapThenReturnOrderLogEntity(){
        OrderLog orderLog = defaultOrderLog().build();

        OrderLogEntity orderLogEntity = this.mapper.mapToEntity(orderLog);

        assertThat(orderLogEntity).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderLogEntity.getDatetime()).isEqualTo(orderLog.getDatetime());
        assertThat(orderLogEntity.getStatus()).isEqualTo(orderLog.getStatus());
    }

    @Test
    @DisplayName("Given Order Log Entity When Map Then Return Order Log")
    public void givenOrderLogEntityWhenMapThenReturnOrderLog(){
        OrderLogEntity orderLogEntity = defaultOrderLogEntity().build();

        OrderLog orderLog = this.mapper.mapToDomain(orderLogEntity);

        assertThat(orderLog).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderLog.getDatetime()).isEqualTo(orderLogEntity.getDatetime());
        assertThat(orderLog.getStatus()).isEqualTo(orderLogEntity.getStatus());
    }
}
