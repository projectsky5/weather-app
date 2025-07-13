package com.projectsky.weatherconsumer.service;

import com.projectsky.common.dto.WeatherEvent;
import com.projectsky.weatherconsumer.enums.Condition;
import com.projectsky.weatherconsumer.dto.AnalyticsResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

    private final Map<Condition, Map<String, Integer>> analytics = new ConcurrentHashMap<>();

    public AnalyticsServiceImpl() {
        for (Condition condition : Condition.values()) {
            analytics.put(condition, new ConcurrentHashMap<>());
        }
    }

    @Override
    public void process(WeatherEvent event) {
        Condition condition = Condition.fromValue(event.condition());

        analytics.get(condition)
                .merge(event.city(), 1, Integer::sum);
    }

    @Override
    public AnalyticsResponse getAnalytics() {
        return new AnalyticsResponse(analytics);
    }
}
