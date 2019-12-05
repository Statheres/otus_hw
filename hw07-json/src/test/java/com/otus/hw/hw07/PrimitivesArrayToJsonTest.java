package com.otus.hw.hw07;

import org.junit.jupiter.api.Test;

import javax.json.JsonValue;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PrimitivesArrayToJsonTest extends AbstractSimpsonTest {
    @Test
    void array() throws IllegalAccessException {
        long[] data = {1, 2};

        assertEquals(
                Arrays.stream(data).boxed().collect(Collectors.toList()),
                asList(simpson.toJson(data), AbstractSimpsonTest::asLong)
        );
    }

    @Test
    void array_empty() throws IllegalAccessException {
        int[] data = {};
        assertTrue(asList(simpson.toJson(data), AbstractSimpsonTest::asLong).isEmpty());
    }
}
