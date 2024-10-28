package br.com.microservices.orchestrated.orchestratorservice.configs.kafka;

import br.com.microservices.orchestrated.orchestratorservice.core.enums.ETopics;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private static final Logger logger = LoggerFactory.getLogger(KafkaConfig.class);

    private static final int PARTITION_COUNT = 1;
    private static final int REPLICA_COUNT = 1;



    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServer;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.consumer.auto-offset-reset}")
    private String autoOffsetReset;




    @Bean
    public ConsumerFactory<String, String> consumerFactory(){
        return new DefaultKafkaConsumerFactory<>(consumerProperties());
    }

    private Map<String, Object> consumerProperties() {
        var props = new HashMap<String, Object>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return props;
    }


    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory(){
        return new DefaultKafkaProducerFactory<>(producerProperties());
    }

    private Map<String, Object> producerProperties() {
        var props = new HashMap<String, Object>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Corrigido de StringDeserializer para StringSerializer
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Corrigido de StringDeserializer para StringSerializer

        return props;
    }

    private NewTopic buildTopic(String name)
    {
        return TopicBuilder
                .name(name)
                .replicas(REPLICA_COUNT)
                .partitions(PARTITION_COUNT)
                .build();
    }


    @Bean
    public NewTopic inventorySuccessTopic(){
        String topic = ETopics.INVENTOY_SUCCESS.name();
        logger.info(String.format("Inventory Success topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic inventoryFailTopic(){
        String topic = ETopics.INVENTOY_FAIL.name();
        logger.info(String.format("Inventory Fail topic: %s", topic));
        return buildTopic(topic);
    }


    @Bean
    public NewTopic startSagaTopic(){
        String topic = ETopics.START_SAGA.name();
        logger.info(String.format("Start saga topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic notifyEndingTopic(){
        String topic = ETopics.NOTIFY_ENDING.name();
        logger.info(String.format("Notify ending topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic orchestrationTopic(){
        String topic = ETopics.BASE_ORCHESTRATOR.name();
        logger.info(String.format("Notify ending topic: %s", topic));
        return buildTopic(topic);
    }


    @Bean
    public NewTopic finishSuccessTopic(){
        String topic = ETopics.FINISH_SUCCESS.name();
        logger.info(String.format("Finish Success topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic finishFailTopic(){
        String topic = ETopics.FINISH_FAIL.name();
        logger.info(String.format("Finish Fail topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic productValidationSuccessTopic() {
        String topic = ETopics.PRODUCT_VALIDATION_SUCCESS.name();
        logger.info(String.format("Product Validation Success topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic productValidationFailTopic() {
        String topic = ETopics.PRODUCT_VALIDATION_FAIL.name();
        logger.info(String.format("Product Validation Fail topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic paymentSuccessTopic() {
        String topic = ETopics.PAYMENT_SUCCESS.name();
        logger.info(String.format("Payment Success topic: %s", topic));
        return buildTopic(topic);
    }

    @Bean
    public NewTopic paymentFailTopic() {
        String topic = ETopics.PAYMENT_FAIL.name();
        logger.info(String.format("Payment Fail topic: %s", topic));
        return buildTopic(topic);
    }



}
