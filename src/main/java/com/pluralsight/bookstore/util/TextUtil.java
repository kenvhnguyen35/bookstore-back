package com.pluralsight.bookstore.util;

/**
 * Created by m05b372 on 7-7-2017.
 */
public class TextUtil {
    public String sanitize(String textToSanitize) {
        return textToSanitize.replaceAll("\\s+", " ");
    }
}
