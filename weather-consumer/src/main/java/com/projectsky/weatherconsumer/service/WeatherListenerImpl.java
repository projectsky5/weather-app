package com.projectsky.weatherconsumer.service;

import com.projectsky.common.dto.WeatherEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherListenerImpl implements WeatherListener {

    private final AnalyticsService analyticsService;

    @Override
    @KafkaListener(topics = "${weather.topic}", groupId = "weather-group")
    public void listen(WeatherEvent event) {
        log.info("Received weather event: {}", event);
        analyticsService.process(event);
    }
}
