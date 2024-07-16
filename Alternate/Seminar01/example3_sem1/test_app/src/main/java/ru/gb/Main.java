package ru.gb;

import com.google.common.base.Joiner;

public class Main {
    public static void main(String[] args) {
        //System.out.println("Hello world!");
        String[] words = {"Hello", "World"};
        String message = Joiner.on(", ").join(words);
        System.out.println(message);

    }
}