package com.ri.eurekaclientuser.util;

public class Random {

    public static int getRandom(int start, int end) {
        return (int) (start + Math.random() * (end - start));
    }
}
