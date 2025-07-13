package com.projectsky.weatherconsumer.dto;

import com.projectsky.weatherconsumer.enums.Condition;

import java.util.Map;

public record AnalyticsResponse(
        Map<Condition, Map<String, Integer>> analytics
) {
}
