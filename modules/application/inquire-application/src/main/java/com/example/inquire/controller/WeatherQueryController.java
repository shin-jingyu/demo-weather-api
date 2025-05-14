package com.example.inquire.controller;

import com.example.inquire.controller.dto.WeatherForecastResponse;
import com.example.inquire.service.WeatherQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherQueryController {

    private final WeatherQueryService weatherQueryService;

    @GetMapping
    public List<WeatherForecastResponse> getWeather() {
        var forecasts = weatherQueryService.getAllForecasts();
        if (forecasts.isEmpty()) throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        return forecasts.stream().map(WeatherForecastResponse::from).toList();
    }
}
