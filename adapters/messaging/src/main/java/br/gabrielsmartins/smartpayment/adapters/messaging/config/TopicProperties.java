package br.gabrielsmartins.smartpayment.adapters.messaging.config;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@ConfigurationProperties(prefix = "topic")
@Getter
public class TopicProperties {

    public static final String ORDER_STATUS_UPDATED = "order-status-updated";
    public static final String ORDER_REQUESTED = "order-requested";
    public static final String ORDER_VALIDATED = "order-validated";

    private Map<String, String> input;
    private Map<String, String> output;

    public String getInputTopic(String topicName) {
        return input.entrySet().stream()
                               .filter(t -> t.getKey().equals(topicName))
                               .findFirst()
                               .orElse(Map.entry("", ""))
                               .getValue();
    }

    public String getOutputTopic(String topicName) {
        return output.entrySet().stream()
                                .filter(t -> t.getKey().equals(topicName))
                                .findFirst()
                                .orElse(Map.entry("", ""))
                                .getValue();
    }
}
