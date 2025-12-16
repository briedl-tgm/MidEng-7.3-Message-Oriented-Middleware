package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

// Logging und Datenspeicherung hinzufügen
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MessageConsumer {

    private static final Logger logger = LoggerFactory.getLogger(MessageConsumer.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    // Zentrale Speicherung der Lagerstandsdaten
    // Verwendung von Collections.synchronizedList für Thread-Sicherheit
    private final List<WarehouseData> allWarehouseData = Collections.synchronizedList(new LinkedList<>());

    public List<WarehouseData> getAllWarehouseData() {
        return allWarehouseData;
    }

    @KafkaListener(topics = "quickstart-events", groupId = "myGroup")
    public void processMessage(String content) {
        logger.info("Read raw message from Kafka: {}", content); // Logging der Rohdaten

        try {
            // JSON-String zurück in WarehouseData-Objekt umwandeln (Deserialisierung)
            WarehouseData receivedData = objectMapper.readValue(content, WarehouseData.class);

            // Daten in der zentralen Liste speichern
            allWarehouseData.add(receivedData);

            logger.info("Successfully processed and stored data: {}", receivedData);
        } catch (Exception e) {
            logger.error("Error processing JSON message: {}", content, e);
        }
    }
}