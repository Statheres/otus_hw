package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import java.util.Map;

class MapEncoder implements JsonEncoder<Map<?, ?>> {
    @Override
    public JsonValue encode(Map<?, ?> value) throws IllegalAccessException {
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();

        for (Map.Entry<?, ?> entry : value.entrySet()) {
            JsonEncoder encoder = JsonEncoderFactory.createEncoder(entry.getValue().getClass());
            objectBuilder.add(
                    entry.getKey().toString(),
                    encoder.encode(entry.getValue())
            );
        }

        return objectBuilder.build();
    }
}
