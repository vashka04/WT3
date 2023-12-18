package com.es.core.entity.ball.sort;

import java.util.Arrays;

public enum SortOrder {
    ASC,
    DESC;
    public static SortOrder getValue(String name) {
        if (name == null){
            return null;
        }
        return Arrays.stream(SortOrder.values())
                .filter(value -> value.name().equals(name.toUpperCase()))
                .findAny()
                .orElse(null);
    }
}
