package com.otus.hw.hw07;

import org.junit.jupiter.api.Test;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ObjectToJsonTest extends AbstractSimpsonTest {
    @Test
    void object() throws IllegalAccessException {
        class Phone {
            String code;
            String number;
        }

        class Person {
            String name;
            int age;
            List<Phone> phoneList = new ArrayList<>();
        }

        Phone phone1 = new Phone();
        phone1.code = "+434";
        phone1.number = "213213123";

        Phone phone2 = new Phone();

        Person person = new Person();
        person.name = "Kostiantyn";
        person.age = 12;
        person.phoneList.add(phone1);
        person.phoneList.add(phone2);

        JsonObject value = (JsonObject) simpson.toJson(person);

        assertEquals(person.name, value.getString("name"));
        assertEquals(person.age, value.getInt("age"));

        JsonArray phoneArray = value.getJsonArray("phoneList");
        for (int i = 0; i < phoneArray.size(); ++i) {
            JsonObject phoneObj = phoneArray.getJsonObject(i);
            Phone phone = person.phoneList.get(i);

            assertEquals(phone.code, asString(phoneObj.get("code")));
            assertEquals(phone.number, asString(phoneObj.get("number")));
        }
    }
}
