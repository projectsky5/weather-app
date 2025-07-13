package com.projectsky.weatherproducer.service;

import com.projectsky.weatherproducer.dto.CityDto;

public interface WeatherProducerService {

    String addCity(CityDto city);

    void send();
}
