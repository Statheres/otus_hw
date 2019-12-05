package com.otus.hw.hw07.simpson.encoder;

import javax.json.Json;
import javax.json.JsonValue;

class StringEncoder implements JsonEncoder<String> {
    @Override
    public JsonValue encode(String value) {
        return Json.createValue(value);
    }
}
