package com.companyname.appname.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;

import de.devland.esperandro.serialization.Serializer;
import lombok.RequiredArgsConstructor;

/**
 * Created by adelnizamutdinov on 12/03/2014
 */
@RequiredArgsConstructor(suppressConstructorProperties = true)
public class JacksonSerializer implements Serializer {
    final ObjectMapper objectMapper;

    public String serialize(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public String serialize(Serializable serializable) {
        return serialize((Object) serializable);
    }

    @Override public <T> T deserialize(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T deserialize(String json, TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(json, typeReference);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}