package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonValue;

class DoubleEncoder implements JsonEncoder<Double> {
    @Override
    public JsonValue encode(Double value) {
        return Json.createValue(value);
    }
}
