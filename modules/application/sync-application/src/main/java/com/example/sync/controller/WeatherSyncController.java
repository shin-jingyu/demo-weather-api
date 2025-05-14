package com.example.sync.controller;

import com.example.sync.service.WeatherSyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherSyncController {

    private final WeatherSyncService weatherSyncService;

    @PostMapping("/sync")
    public ResponseEntity<Void> syncWeather() {
        weatherSyncService.sync();
        return ResponseEntity.ok().build();
    }
}
