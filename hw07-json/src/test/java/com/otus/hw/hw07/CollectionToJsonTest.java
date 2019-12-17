package com.otus.hw.hw07;

import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CollectionToJsonTest extends AbstractSimpsonTest {
    @Test
    void list() throws IllegalAccessException {
        List<Long> list = Arrays.asList(61L, -1L);

        assertEquals(
                list,
                asList(simpson.toJson(list), AbstractSimpsonTest::asLong)
        );
    }

    @Test
    void set() throws IllegalAccessException {
        Set<Long> set = new HashSet<>(Arrays.asList(61L, -1L));

        assertEquals(
                set,
                new HashSet<>(asList(simpson.toJson(set), AbstractSimpsonTest::asLong))
        );
    }

    @Test
    void map() throws  IllegalAccessException {
        Map<String, Object> map = new HashMap<String, Object>()
        {{
            put("String", "Hello World");
            put("List", Arrays.asList(61L, -1L));
        }};

        JsonObject value = (JsonObject) simpson.toJson(map);

        assertEquals(map.get("String"), value.getString("String"));
        assertEquals(map.get("List"), asList(value.getJsonArray("List"), AbstractSimpsonTest::asLong));
    }
}
