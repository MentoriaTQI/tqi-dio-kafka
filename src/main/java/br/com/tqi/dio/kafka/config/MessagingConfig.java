package br.com.tqi.dio.kafka.config;

import br.com.tqi.dio.Employee;
import br.com.tqi.dio.EmployeeKey;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class MessagingConfig implements MessagingConfigPort<EmployeeKey, Employee> {

	private final KafkaProperties kafkaProperties;

	@Bean(name = "configEmployeeProducer")
	@Override
	public KafkaProducer<EmployeeKey, Employee> configureProducer() {
		Properties properties = new Properties();

		properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
		properties.put(ProducerConfig.ACKS_CONFIG, kafkaProperties.getAcksConfig());
		properties.put(ProducerConfig.RETRIES_CONFIG, kafkaProperties.getRetriesConfig());
		properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, kafkaProperties.getKeySerializer());
		properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, kafkaProperties.getValueSerializer());
		properties.put("schema.registry.url", kafkaProperties.getSchemaRegistryUrl());
		properties.put("request.timeout.ms", 300000);
		properties.put("heartbeat.interval.ms", 1000);
		properties.put("max.poll.interval.ms", 900000);
		properties.put("max.poll.records", 100);
		properties.put("session.timeout.ms", 600000);

		return new KafkaProducer<EmployeeKey, Employee>(properties);
	}

}
