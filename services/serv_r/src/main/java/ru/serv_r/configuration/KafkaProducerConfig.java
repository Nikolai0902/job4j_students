package ru.serv_r.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.springframework.kafka.listener.ConcurrentMessageListenerContainer;
import org.springframework.kafka.requestreply.ReplyingKafkaTemplate;

import java.time.Duration;

/**
 * Настройка механизма синхронного обмена сообщениями.
 *
 * ReplyingKafkaTemplate - этот класс расширяет поведение KafkaTemplate, обеспечивая поведение запроса-ответа.
 * Чтобы настроить это, вам понадобится производитель (ProducerFactory) и KafkaMessageListenerContainer.
 * Это интуитивная настройка, поскольку для запроса-ответа необходимо поведение как производителя, так и потребителя.
 *
 * Если у нас есть несколько экземпляров клиента, мы должны убедиться, что ответ отправляется в корректный
 * экземпляр клиента.
 * Документация Spring Kafka предполагает, что каждый консюмер может использовать уникальный топик
 */
@Configuration
public class KafkaProducerConfig {

    @Value("${topic.name.requests.all}")
    public String topicAllRequests;

    @Value("${topic.name.requests.one}")
    public String topicOneRequests;

    @Value("${group.name.replies}")
    public String repliesGroup;

    @Value("${topic.name.replies}")
    public String topiReplies;

    @Bean
    public ReplyingKafkaTemplate<String, String, String> replyKafkaTemplate(
            ProducerFactory<String, String> pf,
            ConcurrentMessageListenerContainer<String, String> container) {
        ReplyingKafkaTemplate<String, String, String> replyingKafkaTemplate = new ReplyingKafkaTemplate<>(pf,container);
        replyingKafkaTemplate.setSharedReplyTopic(true);
        replyingKafkaTemplate.setDefaultReplyTimeout(Duration.ofSeconds(30));
        return replyingKafkaTemplate;
    }

    @Bean
    public ConcurrentMessageListenerContainer<String, String> oneRepliesContainer(
            ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {
        containerFactory.setConcurrency(10);
        ConcurrentMessageListenerContainer<String, String> repliesContainer =
                containerFactory.createContainer(topiReplies);
        repliesContainer.getContainerProperties().setGroupId(repliesGroup);
        repliesContainer.setAutoStartup(false);
        return repliesContainer;
    }

    @Bean
    public NewTopic requestsAll() {
        return TopicBuilder.name(topicAllRequests)
                .partitions(2)
                .build();
    }

    @Bean
    public NewTopic requestsOne() {
        return TopicBuilder.name(topicOneRequests)
                .partitions(2)
                .build();
    }

    @Bean
    public NewTopic replies() {
        return TopicBuilder.name(topiReplies)
                .partitions(2)
                .build();
    }
}
