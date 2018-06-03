package ru.itis.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Date 02.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class MessageEncoder implements Encoder.Text<Message> {
    public void init(EndpointConfig endpointConfig) {

    }

    public String encode(Message message) throws EncodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(message);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void destroy() {

    }
}
