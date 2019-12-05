package com.otus.hw.hw07;

import com.otus.hw.hw07.simpson.Simpson;
import org.junit.jupiter.api.BeforeEach;

import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonString;
import javax.json.JsonValue;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

abstract public class AbstractSimpsonTest {
    protected Simpson simpson;

    @BeforeEach
    public void init() {
        simpson = new Simpson();
    }

    protected static long asLong(JsonValue value) {
        return ((JsonNumber)value).longValue();
    }

    protected static double asDouble(JsonValue value) {
        return ((JsonNumber)value).doubleValue();
    }

    protected static boolean asBool(JsonValue value) {
        return JsonValue.TRUE.equals(value);
    }

    protected static <T> List<T> asList(JsonValue value, Function<JsonValue, T> converter) {
        return ((JsonArray)value).getValuesAs(converter);
    }

    protected static String asString(JsonValue value) {
        return !Objects.isNull(value) ?
                ((JsonString)value).getString() :
                null;
    }
}
