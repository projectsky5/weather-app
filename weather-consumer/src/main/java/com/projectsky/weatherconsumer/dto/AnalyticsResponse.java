package com.projectsky.weatherconsumer.dto;

import com.projectsky.weatherconsumer.constants.Condition;

import java.util.Map;

public record AnalyticsResponse(
        Map<Condition, Map<String, Integer>> analytics
) {
}
