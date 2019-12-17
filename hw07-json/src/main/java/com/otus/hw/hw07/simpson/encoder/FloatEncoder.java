package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonValue;

class FloatEncoder implements JsonEncoder<Float> {
    @Override
    public JsonValue encode(Float value) {
        return Json.createValue(value);
    }
}
