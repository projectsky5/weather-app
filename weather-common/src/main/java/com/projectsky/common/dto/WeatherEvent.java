package com.projectsky.common.dto;

public record WeatherEvent(
        String city,
        int temperature,
        String condition,
        String date
) {
}
