package ru.praimuwka.dynamicatest.config;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    public final String datePattern = "dd.MM.yyyy";

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<Date>() {
            @Override
            public Date parse(String text, Locale locale) throws ParseException {
                SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, locale);
                return dateFormat.parse(text);
            }

            @Override
            public String print(Date date, Locale locale) {
                SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern, locale);
                return dateFormat.format(date);
            }
        });
    }
}
