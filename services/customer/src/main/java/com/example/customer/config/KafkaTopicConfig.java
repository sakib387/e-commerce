package com.example.customer.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic orderNewTopic(){
        return TopicBuilder.name("orderTopic-customer").build();
    }
    @Bean
    public NewTopic cancelNewTopic(){
        return TopicBuilder.name("cancel-order").build();
    }
}
