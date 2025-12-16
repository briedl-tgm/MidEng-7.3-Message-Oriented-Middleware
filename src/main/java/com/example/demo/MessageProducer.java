package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// Logging hinzufügen
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@Component
public class MessageProducer {

    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    // Der KafkaTemplate verwendet String für Key und Value (für JSON-String)
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private static final String TOPIC = "quickstart-events";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/send")
    // Erwartet warehouseId und stockCount als Parameter
    public String sendMessage(@RequestParam(value = "id") String warehouseId,
                              @RequestParam(value = "count") int stockCount) {

        // 1. Datenobjekt erstellen
        WarehouseData data = new WarehouseData(warehouseId, "2025-12-16", stockCount);
        String jsonMessage;

        try {
            // 2. Datenobjekt in JSON-String umwandeln (Serialisierung)
            jsonMessage = objectMapper.writeValueAsString(data);

            // 3. Nachricht senden und loggen
            kafkaTemplate.send(TOPIC, warehouseId, jsonMessage);
            logger.info("Message sent to Kafka topic '{}': {}", TOPIC, jsonMessage); // Logging

            return "Message '" + jsonMessage + "' sent.";
        } catch (Exception e) {
            logger.error("Error sending message: {}", data, e);
            return "Failed to send message.";
        }
    }

}