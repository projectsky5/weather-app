package com.projectsky.weatherconsumer.service;

import com.projectsky.common.dto.WeatherEvent;

public interface WeatherListener {

    void listen(WeatherEvent event);
}
