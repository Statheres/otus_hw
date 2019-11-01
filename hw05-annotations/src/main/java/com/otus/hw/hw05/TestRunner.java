package com.otus.hw.hw05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class TestRunner {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (args.length != 1) {
            throw new IllegalArgumentException("Test class name is not specified");
        }

        String testClassName = args[0];

        Class<?> testClass = Class.forName(testClassName);

        List<Method> testMethods = getAnnotatedMethods(testClass, Test.class);
        List<Method> beforeMethods = getAnnotatedMethods(testClass, Before.class);
        List<Method> afterMethods = getAnnotatedMethods(testClass, After.class);

        System.out.println("-------------------------------------------------------------------------");
        System.out.println("T E S T S");
        System.out.println("-------------------------------------------------------------------------");
        System.out.println(String.format("Running '%s' with %d tests", testClassName, testMethods.size()));

        int passedCount = runTests(
                testClass.getConstructor(),
                beforeMethods,
                testMethods,
                afterMethods
        );

        System.out.println("-------------------------------------------------------------------------");
        System.out.println(String.format("Passed: %d,  Failed: %d", passedCount, testMethods.size() - passedCount));
        System.out.println("-------------------------------------------------------------------------");
    }

    static List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }

    static int runTests(Constructor<?> testConstructor, List<Method> beforeMethods, List<Method> testMethods, List<Method> afterMethods) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        int passedCount = 0;

        for (Method test : testMethods) {
            Object testObject = testConstructor.newInstance();

            for (Method before : beforeMethods) {
                before.invoke(testObject);
            }

            boolean passed = true;
            try {
                test.invoke(testObject);
                ++passedCount;
            } catch (Exception e) {
                passed = false;
            }

            for (Method after : afterMethods) {
                after.invoke(testObject);
            }

            System.out.println(String.format("%s: %s", test.getName(), (passed ? "Passed" : "Failed")));
        }

        return passedCount;
    }
}
