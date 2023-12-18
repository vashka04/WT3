package com.es.core.entity.ball.sort;

import java.util.Arrays;

public enum SortField {
    BRAND,
    MODEL,
    SIZE,
    PRICE;
    public static SortField getValue(String name) {
        if (name == null){
            return null;
        }
        return Arrays.stream(SortField.values())
                .filter(value -> value.name().equals(name.toUpperCase()))
                .findAny()
                .orElse(null);
    }
}
