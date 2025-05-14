package com.example.domain.repository;

import com.example.domain.model.WeatherForecast;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForecastRepository extends JpaRepository<WeatherForecast, Long> {
}
