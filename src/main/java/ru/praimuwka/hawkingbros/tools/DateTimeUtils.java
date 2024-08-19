package ru.praimuwka.hawkingbros.tools;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {
    public static String getCurrentTimeUtcFormatted() {
        LocalDateTime utcNow = LocalDateTime.now(ZoneOffset.UTC);
        return utcNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"));
    }
}
