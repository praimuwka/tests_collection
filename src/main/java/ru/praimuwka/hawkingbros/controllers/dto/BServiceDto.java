package ru.praimuwka.hawkingbros.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BServiceDto {
    private String txt;
    private String createdDt;
    private Integer currentTemp;
}
