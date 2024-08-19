package ru.praimuwka.hawkingbros.services.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GismeteoTemperature {
    private double air;
    private double comfort;
    private double water;
}
