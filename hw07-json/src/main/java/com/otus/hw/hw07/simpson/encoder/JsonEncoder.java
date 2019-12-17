package com.otus.hw.hw07.simpson.encoder;

import javax.json.JsonValue;

public interface JsonEncoder<T> {
    JsonValue encode(T value) throws IllegalAccessException;
}
