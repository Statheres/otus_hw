package com.otus.hw.hw01;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import java.lang.System;
import java.util.List;
import java.util.Map;

class HelloOtus {
    public static void main(String[] args) {
        Map items = ImmutableMap.of("coin", 3, "glass", 4, "pencil", 1);
        items.entrySet()
                .stream()
                .forEach(System.out::println);

        for (String fruit : Lists.newArrayList("orange", "banana", "kiwi", "mandarin", "date", "quince")) {
            System.out.println(fruit);
        }
    }
}
