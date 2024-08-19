package ru.praimuwka.hawkingbros.services.weather;

import javax.naming.ServiceUnavailableException;

import ru.praimuwka.hawkingbros.tools.Coordinates;

public interface WeatherService {
    int getTemperature(Coordinates ccordinates) throws ServiceUnavailableException;
}
