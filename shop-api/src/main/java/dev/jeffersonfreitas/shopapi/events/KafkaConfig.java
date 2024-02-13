package dev.jeffersonfreitas.shopapi.events;

import dev.jeffersonfreitas.shopapi.dto.ShopDTO;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaConfig {

    @Value("${kafka.bootstrapAddress}")
    private String kafkaBootstrapAddress;


    @Bean
    public KafkaTemplate<String, ShopDTO> kafkaTemplate(){
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, ShopDTO> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, ShopDTO> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    public ConsumerFactory<String, ShopDTO> consumerFactory() {
        JsonDeserializer<ShopDTO> deserializer = new JsonDeserializer<>(ShopDTO.class);
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapAddress);
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
    }
    public ProducerFactory<String, ShopDTO> producerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapAddress);
        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        configs.put(ProducerConfig.CLIENT_ID_CONFIG, "shop-api");
        return new DefaultKafkaProducerFactory<>(configs);
    }
}
