package com.example.inquire.service;

import com.example.domain.model.WeatherForecast;
import com.example.domain.repository.ForecastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherQueryService {

    private final ForecastRepository repository;

    public List<WeatherForecast> getAllForecasts() {
        return repository.findAll();
    }
}
