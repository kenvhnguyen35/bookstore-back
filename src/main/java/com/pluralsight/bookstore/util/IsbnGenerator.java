package com.pluralsight.bookstore.util;

import java.util.Random;

/**
 * Created by m05b372 on 7-7-2017.
 */
public class IsbnGenerator implements NumberGenerator {
    @Override
    public String generateNumber() {
        return "13-5677-" + Math.abs(new Random().nextInt());
    }
}
