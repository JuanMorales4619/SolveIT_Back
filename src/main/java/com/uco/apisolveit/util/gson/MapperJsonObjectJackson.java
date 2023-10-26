package com.uco.apisolveit.util.gson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.*;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import org.springframework.stereotype.Component;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class MapperJsonObjectJackson implements MapperJsonObject {
    @Override
    public Optional<String> ejecutar(Object objeto) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return Optional.ofNullable(objectMapper.writeValueAsString(objeto));
        } catch (JsonProcessingException e) {
            return Optional.empty();
        }
    }

    @Override
    public <T> Optional<T> ejecutar(String json, Class<T> claseDestino) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            return Optional.ofNullable(objectMapper.readValue(json, claseDestino));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<String> ejecutarGson(Object objecto) {
        try {
            Gson gson = new GsonBuilder().serializeNulls()
                    .registerTypeAdapter(LocalDateTime.class, new com.google.gson.JsonDeserializer<LocalDateTime>() {
                        @Override
                        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
                            return LocalDateTime.parse(json.getAsString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
                        }
                    })
                    .create();
            String objeto = gson.toJson(objecto);
            return Optional.ofNullable(objeto);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

}
