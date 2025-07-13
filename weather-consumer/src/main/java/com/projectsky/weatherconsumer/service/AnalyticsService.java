package com.projectsky.weatherconsumer.service;

import com.projectsky.common.dto.WeatherEvent;
import com.projectsky.weatherconsumer.dto.AnalyticsResponse;


public interface AnalyticsService {

    void process(WeatherEvent event);

    AnalyticsResponse getAnalytics();

}
