package ru.praimuwka.hawkingbros.services.weather;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.naming.ServiceUnavailableException;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.praimuwka.hawkingbros.tools.Coordinates;
import ru.praimuwka.hawkingbros.tools.StringUtils;

@Primary
@Service
public class GismeteoService implements WeatherService {
    private final String token = "56b30cb255.3443075";
    Pattern pattern = Pattern.compile("(\"air\":\")(.*?)(\"[\\s\\S]?,[\\s\\S]?\"comfort\")");

    @Override
    public int getTemperature(final Coordinates coordinates) throws ServiceUnavailableException {
        RestTemplate restTemplate = new RestTemplate();

        String url = "https://api.gismeteo.ru/";

        Map<String, Object> params = new HashMap<>();
        params.put("longitude", StringUtils.valueOfDoubleWithDot(coordinates.getLongitude()));
        params.put("latitude", StringUtils.valueOfDoubleWithDot(coordinates.getLatitude()));

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Gismeteo-Token", token);
        httpHeaders.add("Accept-Encoding", "deflate");

        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class, params, httpHeaders);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            Matcher matcher = pattern.matcher(responseBody);
            if (matcher.find()) {
                return Integer.parseInt(matcher.group(1));
            }
        }
        throw new ServiceUnavailableException("Cant read response");
    }
}
