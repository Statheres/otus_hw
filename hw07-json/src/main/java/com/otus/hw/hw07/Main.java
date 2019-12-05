package com.otus.hw.hw07;

import com.google.gson.Gson;
import com.otus.hw.hw07.simpson.Simpson;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        Simpson simpson = new Simpson();
        System.out.println(simpson.toJson(Arrays.asList(1, 2, -89)));
    }
}
