package ru.praimuwka.hawkingbros.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.praimuwka.hawkingbros.controllers.dto.AServiceDto;
import ru.praimuwka.hawkingbros.controllers.dto.BServiceDto;
import ru.praimuwka.hawkingbros.services.BService;
import ru.praimuwka.hawkingbros.services.weather.WeatherService;
import ru.praimuwka.hawkingbros.tools.DateTimeUtils;

@RestController
public class AdapterController {
    WeatherService weatherService;
    BService bService;

    @Autowired
    public AdapterController(final WeatherService weatherService, final BService bService) {
        this.weatherService = weatherService;
        this.bService = bService;
    }

    @PostMapping("/adapter/post")
    public ResponseEntity<Void> modifyMessageAndSend(@RequestBody AServiceDto input) {
        System.out.println(input);
        BServiceDto toSend = new BServiceDto(
            input.getMsg(),
            DateTimeUtils.getCurrentTimeUtcFormatted(),
            weatherService.getTemperature(input.getCoordinates())
        );
        // send req to service B

        return ResponseEntity.ok().build();
    }
}
