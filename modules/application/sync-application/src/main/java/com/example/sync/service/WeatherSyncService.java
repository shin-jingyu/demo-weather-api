package com.example.sync.service;

import com.example.domain.model.WeatherForecast;
import com.example.domain.repository.ForecastRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.sync.client.WeatherApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherSyncService {

    private final WeatherApiClient apiClient;
    private final ForecastRepository repository;
    private final ObjectMapper objectMapper;

    public void sync() {
        String baseDate = getBaseDate();
        String baseTime = getTime();

        String response = apiClient.fetchForecast(baseDate, baseTime, 61, 131); // 날짜는 나중에 유틸로
        log.debug("API 응답 확인: \n{}", response);

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode items = root
                    .path("response")
                    .path("body")
                    .path("items")
                    .path("item");

            List<WeatherForecast> forecasts = StreamSupport.stream(items.spliterator(), false)
                    .map(this::buildForecast)
                    .collect(Collectors.toList());

            repository.saveAll(forecasts);
        } catch (Exception e) {
            throw new RuntimeException("Weather error: ", e);
        }
    }

    public WeatherForecast buildForecast(JsonNode node) {
        return WeatherForecast.builder()
                .category(node.path("category").asText())
                .forecastDate(node.path("fcstDate").asText())
                .forecastTime(node.path("fcstTime").asText())
                .forecastValue(node.path("fcstValue").asText())
                .nx(node.path("nx").asInt())
                .ny(node.path("ny").asInt())
                .build();
    }

    public String getBaseDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public String getTime() {
        int[] baseTime = {23, 20, 17, 14, 11, 8, 5, 2};
        int nowTime = LocalTime.now().getHour();

        for (int h : baseTime) {
            if (nowTime >= h + 1) {
                return String.format("%02d00", h);
            }
        }
        return "2300";
    }
}
