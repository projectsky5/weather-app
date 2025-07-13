package com.projectsky.weatherconsumer.service;

import com.projectsky.common.dto.WeatherEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WeatherListenerImpl implements WeatherListener {

    private final AnalyticsService analyticsService;

    @Override
    @KafkaListener(topics = "${weather.topic}", groupId = "weather-group")
    public void listen(WeatherEvent event) {
        analyticsService.process(event);
    }
}
