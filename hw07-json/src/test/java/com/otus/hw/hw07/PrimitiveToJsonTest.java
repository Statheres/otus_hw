package com.otus.hw.hw07;

import org.junit.jupiter.api.Test;

import javax.json.JsonValue;

import static org.junit.jupiter.api.Assertions.*;

public class PrimitiveToJsonTest extends AbstractSimpsonTest {
    @Test
    void null_value() throws IllegalAccessException {
        assertEquals(JsonValue.NULL, simpson.toJson(null));
    }

    @Test
    public void bool_true() throws IllegalAccessException {
        assertTrue(asBool(simpson.toJson(true)));
    }

    @Test
    void bool_false() throws IllegalAccessException {
        assertFalse(asBool(simpson.toJson(false)));
    }

    @Test
    public void string() throws IllegalAccessException {
        assertEquals("\"test string\"", simpson.toJson("test string").toString());
    }

    @Test
    public void string_empty() throws IllegalAccessException {
        assertEquals("\"\"", simpson.toJson("").toString());
    }

    @Test
    public void byte_positive() throws IllegalAccessException {
        assertEquals(Byte.MAX_VALUE, asLong(simpson.toJson(Byte.MAX_VALUE)));
    }

    @Test
    public void byte_negative() throws IllegalAccessException {
        assertEquals(Byte.MIN_VALUE, asLong(simpson.toJson(Byte.MIN_VALUE)));
    }

    @Test
    public void short_positive() throws IllegalAccessException {
        assertEquals(Short.MAX_VALUE, asLong(simpson.toJson(Short.MAX_VALUE)));
    }

    @Test
    public void short_negative() throws IllegalAccessException {
        assertEquals(Short.MIN_VALUE, asLong(simpson.toJson(Short.MIN_VALUE)));
    }

    @Test
    public void int_positive() throws IllegalAccessException {
        assertEquals(Integer.MAX_VALUE, asLong(simpson.toJson(Integer.MAX_VALUE)));
    }

    @Test
    public void int_negative() throws IllegalAccessException {
        assertEquals(Integer.MIN_VALUE, asLong(simpson.toJson(Integer.MIN_VALUE)));
    }

    @Test
    public void long_positive() throws IllegalAccessException {
        assertEquals(Long.MAX_VALUE, asLong(simpson.toJson(Long.MAX_VALUE)));
    }

    @Test
    public void long_negative() throws IllegalAccessException {
        assertEquals(Long.MIN_VALUE, asLong(simpson.toJson(Long.MIN_VALUE)));
    }

    @Test
    public void float_positive() throws IllegalAccessException {
        assertEquals(Float.MAX_VALUE, asDouble(simpson.toJson(Float.MAX_VALUE)));
    }

    @Test
    public void float_negative() throws IllegalAccessException {
        assertEquals(Float.MIN_VALUE, asDouble(simpson.toJson(Float.MIN_VALUE)));
    }

    @Test
    public void double_positive() throws IllegalAccessException {
        assertEquals(Double.MAX_VALUE, asDouble(simpson.toJson(Double.MAX_VALUE)));
    }

    @Test
    public void double_negative() throws IllegalAccessException {
        assertEquals(Double.MIN_VALUE, asDouble(simpson.toJson(Double.MIN_VALUE)));
    }
}
