package com.otus.hw.hw04.mycoolpackage;

import com.otus.hw.hw04.Log;

public class MyCoolInterfaceImpl implements MyCoolInterface {
    @Log
    @Override
    public String doSomething() {
        System.out.println("Doing something");
        return "Some weird string";
    }

    @Override
    public void doAnything() {
        System.out.println("Doing anything");
    }

    @Log
    @Override
    public void doSomethingElse(int arg0, String arg1) {
        System.out.println("Doing something with arg0");
        System.out.println("Doing something with arg1");
    }
}
