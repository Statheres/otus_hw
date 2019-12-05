package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Field;

class ObjectEncoder implements JsonEncoder<Object> {
    @Override
    public JsonValue encode(Object value) throws IllegalAccessException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        for (Field field : value.getClass().getDeclaredFields()) {
            boolean canAccess = field.canAccess(value);

            field.setAccessible(true);

            Object fieldValue = field.get(value);
            if (fieldValue != null) {
                JsonEncoder encoder = JsonEncoderFactory.createEncoder(fieldValue.getClass());
                objectBuilder.add(
                        field.getName(),
                        encoder.encode(fieldValue)
                );
            }

            field.setAccessible(canAccess);
        }
        return objectBuilder.build();
    }
}
