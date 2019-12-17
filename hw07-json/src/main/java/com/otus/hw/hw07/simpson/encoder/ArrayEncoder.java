package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Array;

class ArrayEncoder implements JsonEncoder<Object> {
    @Override
    public JsonValue encode(Object value) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        int arrayLength = Array.getLength(value);
        for (int i = 0; i < arrayLength; ++i) {
            Object element = Array.get(value, i);

            JsonEncoder encoder = JsonEncoderFactory.createEncoder(element.getClass());
            arrayBuilder.add(encoder.encode(element));
        }

        return arrayBuilder.build();
    }
}
