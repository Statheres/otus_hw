package com.otus.hw.hw07.simpson;

import com.otus.hw.hw07.simpson.encoder.JsonEncoder;
import com.otus.hw.hw07.simpson.encoder.JsonEncoderFactory;

import javax.json.JsonValue;

public class Simpson {
    public JsonValue toJson(Object value) throws IllegalAccessException {
        if (value == null) {
            return JsonValue.NULL;
        }

        JsonEncoder encoder = JsonEncoderFactory.createEncoder(value.getClass());
        return encoder.encode(value);
    }
}
