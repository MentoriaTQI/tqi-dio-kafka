package br.com.tqi.dio.kafka.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.Producer;

public interface MessagingConfigPort<T extends SpecificRecordBase, V extends SpecificRecordBase> {

	Producer<T, V> configureProducer();

}
