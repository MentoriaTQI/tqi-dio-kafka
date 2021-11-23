package br.com.tqi.dio.kafka.broken;

import br.com.tqi.dio.Employee;
import br.com.tqi.dio.EmployeeKey;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Service
public class EmployeeProducer {

	private final KafkaProducer<EmployeeKey, Employee> kafkaProducer;

	private final Properties properties;

	private final String topicName;

	private String keySerializer = KafkaAvroSerializer.class.getName();

	private String valueSerializer = KafkaAvroSerializer.class.getName();

	public EmployeeProducer(@Qualifier("configEmployeeProducer") KafkaProducer<EmployeeKey, Employee> kafkaProducer,
			Properties properties, @Value("${kafka.topics.employee}") String topicName) {
		this.kafkaProducer = kafkaProducer;
		this.properties = new Properties();
		this.topicName = topicName;
	}

	@PostConstruct
	public void init() {
		properties.put("key.serializer", keySerializer);
		properties.put("value.serializer", valueSerializer);
		// kafkaProducer = new KafkaProducer(properties);
	}

	public void produceEmployeeDetails(int empId, String firstName, String lastName) {
		// creating employee details
		Employee employee = new Employee();
		employee.setId(empId);
		employee.setFirstName(firstName);
		employee.setLastName(lastName);

		// creating partition key for kafka topic
		EmployeeKey employeeKey = new EmployeeKey();
		employeeKey.setId(empId);
		employeeKey.setDepartmentName("IT");

		kafkaProducer.send(new ProducerRecord(topicName, employeeKey, employee));
	}

}