package com.projectsky.weatherconsumer.service;

import com.projectsky.common.dto.WeatherEvent;
import com.projectsky.weatherconsumer.constants.Condition;
import com.projectsky.weatherconsumer.dto.AnalyticsResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final Map<Condition, Map<String, Integer>> analytics = new ConcurrentHashMap<>();

    @Override
    public void process(WeatherEvent event) {
        Condition condition = Condition.fromValue(event.condition());

        switch (condition) {
            case SUNNY -> analytics.get(Condition.SUNNY)
                    .merge(event.city(), 1, Integer::sum);
            case CLOUDY -> analytics.get(Condition.CLOUDY)
                    .merge(event.city(), 1, Integer::sum);
            case RAINY -> analytics.get(Condition.RAINY)
                    .merge(event.city(), 1, Integer::sum);
            default -> throw new IllegalArgumentException("Unknown condition: " + condition);
        }
    }

    @Override
    public AnalyticsResponse getAnalytics() {
        return new AnalyticsResponse(analytics);
    }
}
