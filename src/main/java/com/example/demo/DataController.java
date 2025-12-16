package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/warehouse")
public class DataController {

    @Autowired
    private MessageConsumer consumer;

    @GetMapping("/status")
    // Gibt die gesammelten Lagerstandsdaten als JSON zurück
    public List<WarehouseData> getWarehouseStatus() {
        return consumer.getAllWarehouseData();
    }
}