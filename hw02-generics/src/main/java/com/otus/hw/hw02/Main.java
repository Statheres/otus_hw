package com.otus.hw.hw02;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

interface Test {
    void run();
}

class Main {
    public static void main(String[] args) {
        Map<String, Test> tests = new LinkedHashMap<>();
        tests.put("test_add", Main::test_add);
        tests.put("test_addInPlace", Main::test_addInPlace);
        tests.put("test_addAll", Main::test_addAll);
        tests.put("test_addAllInPlace", Main::test_addAllInPlace);
        tests.put("test_get", Main::test_get);
        tests.put("test_remove", Main::test_remove);
        tests.put("test_removeAll", Main::test_removeAll);
        tests.put("test_retainAll", Main::test_retainAll);
        tests.put("test_contains", Main::test_contains);
        tests.put("test_containsAll", Main::test_containsAll);
        tests.put("test_indexOf", Main::test_indexOf);
        tests.put("test_lastIndexOf", Main::test_lastIndexOf);
        tests.put("test_clear", Main::test_clear);
        tests.put("test_set", Main::test_set);

        for (Map.Entry<String, Test> testData : tests.entrySet()) {
            System.out.println(String.format("======== %-32s ========", testData.getKey()));
            testData.getValue().run();
            System.out.println("=".repeat(50));
        }
    }

    private static void test_add() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        System.out.println(list.isEmpty());
        for (int i = 0; i < 30; ++i) {
            list.add(i);
        }
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
        System.out.println(list.isEmpty());
    }

    private static void test_addInPlace() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        for (int i = 0; i < 30; ++i) {
            list.add(i % 5, i);
        }
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_addAll() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_addAllInPlace() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list.addAll(1, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list.addAll(5, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_get() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(0, Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        for (int i = 0; i < list.size(); ++i) {
            System.out.print(list.get(i));
            System.out.print(' ');
        }
        System.out.println();
    }

    private static void test_remove() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        list.remove(0);
        list.remove(Integer.valueOf(7));
        list.remove(Integer.valueOf(8));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_removeAll() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        for (int i = 0; i < 30; ++i) {
            list.add(i);
        }
        list.removeAll(Arrays.asList(2, 4, 10, 15, 16, 17, 18, 29, 30));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_retainAll() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        for (int i = 0; i < 30; ++i) {
            list.add(i);
        }
        list.retainAll(Arrays.asList(2, 4, 10, 15, 16, 17, 18, 29, 30));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_contains() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        for (int i = 0; i < 30; ++i) {
            list.add(i);
        }
        System.out.println(list.contains(1));
        System.out.println(list.contains(26));
        System.out.println(list.contains(35));
    }

    private static void test_containsAll() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(list.containsAll(Arrays.asList(1, 5, 2, 3)));
        System.out.println(list.containsAll(Arrays.asList(-1, 25, 2, 3)));
    }

    private static void test_indexOf() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(1, 1, 3, 2, 5, 6, 1, 8, 10, 10));
        System.out.println(list.indexOf(1));
        System.out.println(list.indexOf(5));
        System.out.println(list.indexOf(10));
        System.out.println(list.indexOf(20));
    }

    private static void test_lastIndexOf() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(0, 1, 3, 2, 5, 6, 1, 8, 10, 10));
        System.out.println(list.lastIndexOf(10));
        System.out.println(list.lastIndexOf(5));
        System.out.println(list.lastIndexOf(1));
        System.out.println(list.lastIndexOf(0));
        System.out.println(list.lastIndexOf(12));
    }

    private static void test_clear() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(0, 1, 3, 2, 5, 6, 1, 8, 10, 10));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
        list.clear();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
        list.add(1);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(list.size());
    }

    private static void test_set() {
        DIYarrayList<Integer> list = new DIYarrayList<>();
        list.addAll(Arrays.asList(0, 1, 3, 2, 5, 6, 1, 8, 10, 10));
        list.set(0, 1);
        list.set(4, 4);
        list.set(9, 9);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
