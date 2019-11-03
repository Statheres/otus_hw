package com.otus.hw.hw05;

public class Main {

    public static void main(String[] args) throws NoSuchMethodException, ClassNotFoundException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Test class name is not specified");
        }

        new TestRunner(args[0]).run();
    }
}
