package ru.itis;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.io.IOException;

/**
 * Date 02.06.2018
 *
 * @author Hursanov Sulaymon
 * @version v1.0
 **/
public class MessageDecoder implements Decoder.Text<Message> {

    public void init(EndpointConfig endpointConfig) {

    }

    public Message decode(String messageJson) throws DecodeException {
        ObjectMapper objectMapper = new ObjectMapper();
        Message message = null;
        try {
            message = objectMapper.readValue(messageJson, Message.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }

    public boolean willDecode(String s) {
        return true;
    }

    public void destroy() {

    }
}
