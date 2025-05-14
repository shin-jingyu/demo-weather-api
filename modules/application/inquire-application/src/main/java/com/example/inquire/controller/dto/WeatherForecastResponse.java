package com.example.inquire.controller.dto;

import com.example.domain.model.WeatherForecast;

public record WeatherForecastResponse(
        String category,
        String forecastDate,
        String forecastTime,
        String forecastValue,
        int nx,
        int ny
) {
    public static WeatherForecastResponse from(WeatherForecast entity) {
        return new WeatherForecastResponse(
                entity.getCategory(),
                entity.getForecastDate(),
                entity.getForecastTime(),
                entity.getForecastValue(),
                entity.getNx(),
                entity.getNy()
        );
    }
}
