package ru.praimuwka.hawkingbros.services.weather;

import java.net.http.HttpHeaders;
import java.util.*;
import javax.naming.ServiceUnavailableException;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.praimuwka.hawkingbros.services.weather.dto.GismeteoTemperature;
import ru.praimuwka.hawkingbros.tools.Coordinates;

@Primary
@Service
public class GismeteoService implements WeatherService {
    private final String token = "56b30cb255.3443075";
    private final RestTemplate restTemplate;

    public GismeteoService() {
        restTemplate = new RestTemplate();
        restTemplate.setDefaultUriVariables(
            new HashMap<String, String>() {{
                put("apiGismeteo", "https://api.gismeteo.ru/");
            }}
        );
    }

    @Override
    public int getTemperature(final Coordinates coordinates) /*throws ServiceUnavailableException*/ {
        // var requestBody = new HashMap<String, String>() {{
        //     put("latitude", String.valueOf(coordinates.getLatitude()));
        //     put("longitude", String.valueOf(coordinates.getLongitude()));
        // }};
        //
        // RestTemplate restTemplate = new RestTemplate();
        //
        // HttpHeaders headers = new HttpHeaders();
        // headers.setContentType(MediaType.APPLICATION_JSON);
        // headers.add("Accept-Encoding", "gzip");
        // headers.add("Accept-Encoding", "gzip");
        // HttpEntity<GismeteoTemperature> requestEntity = new HttpEntity<>(requestBody, headers);
        //
        // String url = "http://example.com/api/endpoint"; // Replace with your actual URL
        //
        // try {
        //     GismeteoTemperature response = restTemplate.postForObject(url, requestEntity, GismeteoTemperature.class);
        //     System.out.println("Response: " + response.getValue1() + ", " + response.getValue2());
        // } catch (Exception e) {
        //     System.err.println("Error occurred: " + e.getMessage());
        // }
        return 0;
    }
}
