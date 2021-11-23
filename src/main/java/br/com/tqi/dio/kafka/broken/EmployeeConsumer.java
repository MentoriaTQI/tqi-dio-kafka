package br.com.tqi.dio.kafka.broken;

import br.com.tqi.dio.Employee;
import br.com.tqi.dio.EmployeeKey;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeConsumer {

	@KafkaListener(topics = "${kafka.topics.employee}", groupId = "tqi_dio")
	public void consumeEmployeeDetails(ConsumerRecord<EmployeeKey, Employee> payload) {
		log.info("key: {}", payload.key());
		log.info("Headers: {}", payload.headers());
		log.info("Partition: {}", payload.partition());
		log.info("Let's process employee details: {}", payload.value());
		// TODO GRAVAR NO BANCO DE DADOS
	}

}
