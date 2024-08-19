package ru.praimuwka.hawkingbros.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.praimuwka.hawkingbros.controllers.dto.MsgB;

@Service
public class BService {
    public void send(MsgB dto) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForEntity("http://bservice.url/endpoint", dto, dto.getClass());
    }
}
