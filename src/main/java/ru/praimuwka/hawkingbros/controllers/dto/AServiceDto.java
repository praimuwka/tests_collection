package ru.praimuwka.hawkingbros.controllers.dto;

import ru.praimuwka.hawkingbros.tools.Coordinates;
import ru.praimuwka.hawkingbros.tools.Language;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AServiceDto {
        private String msg;
        private Language lng;
        private Coordinates coordinates;
}
