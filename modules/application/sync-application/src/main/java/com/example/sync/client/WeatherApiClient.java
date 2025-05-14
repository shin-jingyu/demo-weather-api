package com.example.sync.client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@RequiredArgsConstructor
public class WeatherApiClient {

    private final RestTemplate restTemplate;

    @Value("${weather.api.service-key}")
    private String serviceKey;

    private static final String BASE_URL =
            "https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst";

    public String fetchForecast(String baseDate, String baseTime, int nx, int ny) {

        StringBuilder urlBuilder = new StringBuilder(BASE_URL);
        urlBuilder.append("?serviceKey=").append(serviceKey);
        urlBuilder.append("&numOfRows=1");
        urlBuilder.append("&pageNo=1");
        urlBuilder.append("&dataType=JSON");
        urlBuilder.append("&base_date=").append(baseDate);
        urlBuilder.append("&base_time=").append(baseTime);
        urlBuilder.append("&nx=").append(nx);
        urlBuilder.append("&ny=").append(ny);

        String url = urlBuilder.toString();
        log.debug("url: {}",url);

        try {
            String responseBody = restTemplate.getForObject(url, String.class);
            log.debug("responseBody: {}", responseBody);
            return responseBody;
        } catch (Exception e) {
            log.error("restTemplate error: ", e);
            throw e;
        }
    }
}
