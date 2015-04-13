package com.flatstack.android.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;

import java.io.IOException;
import java.util.Locale;

/**
 * Created by IlyaEremin on 13/04/15.
 */
public class EnumDeserializer extends BeanDeserializerModifier {
    @Override public JsonDeserializer<Enum> modifyEnumDeserializer(
        DeserializationConfig config, JavaType type,
        BeanDescription beanDesc, JsonDeserializer<?> deserializer) {

        return new JsonDeserializer<Enum>() {
            @Override public Enum deserialize(JsonParser jp,
                                              DeserializationContext ctxt) throws IOException {
                @SuppressWarnings("unchecked")
                final Class<? extends Enum> rawClass = (Class<Enum<?>>) type.getRawClass();
                return Enum.valueOf(rawClass, jp.getValueAsString().toUpperCase(Locale.US));
            }
        };
    }
}
