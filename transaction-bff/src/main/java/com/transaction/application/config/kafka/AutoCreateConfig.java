package com.transaction.application.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
@Profile("dev")
public class AutoCreateConfig {

    @Value("${topic.transaction.name}")
    public String topic;

    @Value("${topic.transaction.partitions}")
    public Integer partitions;

    @Value("${topic.transaction.replicas}")
    public Integer replicas;

    @Bean
    public NewTopic transactionTopicEvent() {
        return TopicBuilder
            .name(topic)
            .partitions(partitions)
            .replicas(replicas)
            .build();
    }

}
