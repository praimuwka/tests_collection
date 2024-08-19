package ru.praimuwka.hawkingbros.tools;

public class StringUtils {
    public static String valueOfDoubleWithDot(double number) {
        return String.valueOf(number).replace(",", ".");
    }
}
