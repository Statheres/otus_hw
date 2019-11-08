package com.otus.hw.hw04.proxydemo;

import com.otus.hw.hw04.Log;
import com.otus.hw.hw04.mycoolpackage.MyCoolInterface;
import com.otus.hw.hw04.mycoolpackage.MyCoolInterfaceImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class SimpleIoCContainer {
    MyCoolInterface createMyCoolObject() {
        return (MyCoolInterface) Proxy.newProxyInstance(
                MyCoolInterface.class.getClassLoader(),
                new Class<?>[]{MyCoolInterface.class},
                new DynamicInvocationHandler(new MyCoolInterfaceImpl())
        );
    }

    private static class DynamicInvocationHandler implements InvocationHandler {
        private Object context;

        private Set<String> loggedMethodNames;

        DynamicInvocationHandler(Object context) {
            this.context = context;

            loggedMethodNames = new HashSet<>();
            for (Method method : context.getClass().getDeclaredMethods()) {
                if (method.isAnnotationPresent(Log.class)) {
                    loggedMethodNames.add(method.getName());
                }
            }
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (loggedMethodNames.contains(method.getName())) {
                if (args == null || args.length == 0) {
                    System.out.println(String.format("%s method called", method.getName()));
                } else {
                    System.out.println(String.format("%s method called with args: %s", method.getName(), Arrays.toString(args)));
                }
            }
            return method.invoke(context, args);
        }
    }
}
