package com.projectsky.weatherproducer.service;

import com.projectsky.common.dto.WeatherEvent;
import com.projectsky.weatherproducer.dto.CityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class WeatherProducerServiceImpl implements WeatherProducerService {

    // Spring обертка над KafkaProducer
    private final KafkaTemplate<String, WeatherEvent> kafkaTemplate;

    // Получение топика из конфига
    @Value("${weather.topic}")
    private String topic;

    private final Set<String> cities = new HashSet<>(List.of("Магадан", "Чукотка", "Санкт-Петербург", "Тюмень", "Москва"));
    private final List<String> conditions = List.of("Солнечно", "Облачно", "Дождь");

    // Проверка на дубликат и ответ 409 отсутствует (мвп)
    @Override
    public String addCity(CityDto dto) {
        String city = dto.city();
        cities.add(city);

        return city;
    }

    /*
    * Генерирует WeatherEvent который является Java-проекцией на передаваемый JSON
    * Сериализация происходит с помощью настроек kafka: key и value сериализаторов и десериализаторов
    * */
    @Override
    @Scheduled(fixedRate = 3000)
    public void send() {
        String city = pickRandom(new ArrayList<>(cities));
        String condition = pickRandom(conditions);
        int temperature = ThreadLocalRandom.current().nextInt(0, 36);
        String date = LocalDate.now().toString();

        WeatherEvent event = new WeatherEvent(city, temperature, condition, date);
        kafkaTemplate.send(topic, event);
    }

    // Параметризованный метод для возвращения рандомного значения из любого передаваемого списка
    private <T> T pickRandom(List<T> list) {
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
