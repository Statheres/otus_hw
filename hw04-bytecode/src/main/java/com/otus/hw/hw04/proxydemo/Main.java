package com.otus.hw.hw04.proxydemo;

import com.otus.hw.hw04.mycoolpackage.MyCoolInterface;

class Main {
    public static void main(String[] args) {
        SimpleIoCContainer container = new SimpleIoCContainer();
        MyCoolInterface myCoolInterface = container.createMyCoolObject();
        myCoolInterface.doSomething();
        myCoolInterface.doSomethingElse(42, "Ops");
        myCoolInterface.doAnything();
    }
}
