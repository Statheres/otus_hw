package com.otus.hw.hw07.simpson.encoder;

import javax.json.JsonValue;

class BooleanEncoder implements JsonEncoder<Boolean> {
    @Override
    public JsonValue encode(Boolean value) {
        return value ? JsonValue.TRUE : JsonValue.FALSE;
    }
}
