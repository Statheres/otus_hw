package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonValue;

class LongEncoder implements JsonEncoder<Number> {
    @Override
    public JsonValue encode(Number value) {
        return Json.createValue(value.longValue());
    }
}
