package com.projectsky.weatherproducer.controller;

import com.projectsky.weatherproducer.dto.CityDto;
import com.projectsky.weatherproducer.service.WeatherProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cities")
@RequiredArgsConstructor
public class CityController {

    private final WeatherProducerService service;

    // Всегда возвращает 201 Created, т.к не настроена обработка дублирования
    @PostMapping
    public ResponseEntity<String> addCity(
            @RequestBody CityDto dto
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.addCity(dto));
    }
}
