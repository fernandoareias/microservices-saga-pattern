package br.com.microservices.orchestrated.productvalidationservice.core.utils;

import br.com.microservices.orchestrated.productvalidationservice.core.document.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class JsonUtil {

    private final ObjectMapper objectMapper;

    public String toJson(Object object) throws JsonProcessingException {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw e;  
        }
    }

    public Event toEvent(String json) throws JsonProcessingException {
        try {
            return objectMapper.readValue(json, Event.class);
        } catch (JsonProcessingException e) {
            throw e;
        }
    }
}
