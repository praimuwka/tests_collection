package ru.praimuwka.hawkingbros.controllers;

import javax.naming.ServiceUnavailableException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.praimuwka.hawkingbros.controllers.dto.MsgA;
import ru.praimuwka.hawkingbros.controllers.dto.MsgB;
import ru.praimuwka.hawkingbros.services.BService;
import ru.praimuwka.hawkingbros.services.weather.WeatherService;
import ru.praimuwka.hawkingbros.tools.DateTimeUtils;
import ru.praimuwka.hawkingbros.tools.Language;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/adapter")
public class AdapterController {
    WeatherService weatherService;
    BService bService;

    @Autowired
    public AdapterController(final WeatherService weatherService, final BService bService) {
        this.weatherService = weatherService;
        this.bService = bService;
    }

    @PostMapping("/post")
    @Operation(summary = "Обогатить запрос от сервиса А и переслать сервису B", responses = {
        @ApiResponse(responseCode = "204", description = "Язык не обслуживается"),
        @ApiResponse(responseCode = "400", description = "Некорректные входные данные"),
        @ApiResponse(responseCode = "504", description = "Сервис недоступен"),
    })
    public ResponseEntity<Void> modifyMessageAndSend(@RequestBody MsgA input) {
        if (!input.getLng().equals(Language.RUSSIAN)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        if (input.getMsg().isBlank()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MsgB toSend;
        try {
            toSend = new MsgB(
                input.getMsg(),
                DateTimeUtils.getCurrentTimeUtcFormatted(),
                weatherService.getTemperature(input.getCoordinates())
            );
        } catch (ServiceUnavailableException e) {
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        bService.send(toSend);
        return ResponseEntity.ok().build();
    }
}
