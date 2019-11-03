package com.otus.hw.hw05;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

class TestRunner {
    private List<Method> testMethods;
    private List<Method> beforeMethods;
    private List<Method> afterMethods;
    private Class<?> testClass;
    private Constructor<?> testConstructor;

    TestRunner(String testClassName) throws ClassNotFoundException, NoSuchMethodException {
        testClass = Class.forName(testClassName);

        testMethods = getAnnotatedMethods(testClass, Test.class);
        beforeMethods = getAnnotatedMethods(testClass, Before.class);
        afterMethods = getAnnotatedMethods(testClass, After.class);

        testConstructor = testClass.getConstructor();
        testConstructor.setAccessible(true);
    }

    void run() {
        System.out.println("#########################################################################");
        System.out.println("T E S T S");
        System.out.println("#########################################################################");
        System.out.println(String.format("Running '%s' with %d tests", testClass.getName(), testMethods.size()));
        System.out.println("#########################################################################");

        int passedCount = runAllTests();

        System.out.println("#########################################################################");
        System.out.println(String.format("Passed: %d,  Failed: %d", passedCount, testMethods.size() - passedCount));
        System.out.println("#########################################################################");
    }

    private List<Method> getAnnotatedMethods(Class<?> clazz, Class<? extends Annotation> annotationClass) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : clazz.getMethods()) {
            if (method.isAnnotationPresent(annotationClass)) {
                annotatedMethods.add(method);
            }
        }

        return annotatedMethods;
    }

    private int runAllTests() {
        int passedCount = 0;

        for (Method test : testMethods) {
            try {
                Object testObject = testConstructor.newInstance();

                try {
                    setUpTest(testObject);

                    test.invoke(testObject);
                } finally {
                    tearDownTest(testObject);
                }

                ++passedCount;

                System.out.println(String.format("%s: Passed", test.getName()));
            } catch (Exception e) {
                System.out.println(String.format("%s: Failed %s%s", test.getName(), System.lineSeparator(), e));
            }

            System.out.println("-------------------------------------------------------------------------");
        }

        return passedCount;
    }

    private void setUpTest(Object testObject) throws InvocationTargetException, IllegalAccessException {
        for (Method before : beforeMethods) {
            before.invoke(testObject);
        }
    }

    private void tearDownTest(Object testObject) throws InvocationTargetException, IllegalAccessException {
        for (Method after : afterMethods) {
            after.invoke(testObject);
        }
    }
}
