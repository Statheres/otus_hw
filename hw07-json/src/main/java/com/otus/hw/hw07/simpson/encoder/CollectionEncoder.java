package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonValue;
import java.lang.reflect.Array;
import java.util.Collection;

class CollectionEncoder implements JsonEncoder<Collection> {
    @Override
    public JsonValue encode(Collection value) throws IllegalAccessException {
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Object element : value) {
            JsonEncoder encoder = JsonEncoderFactory.createEncoder(element.getClass());
            arrayBuilder.add(encoder.encode(element));
        }

        return arrayBuilder.build();
    }
}
