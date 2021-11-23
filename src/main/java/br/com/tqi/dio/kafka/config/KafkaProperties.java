package br.com.tqi.dio.kafka.config;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Data
@NoArgsConstructor
public class KafkaProperties {

	private List<String> bootstrapServers;

	private String acksConfig;

	private String retriesConfig;

	private Class<?> keySerializer = KafkaAvroSerializer.class;

	private Class<?> valueSerializer = KafkaAvroSerializer.class;

	private String schemaRegistryUrl;

}
