package ru.calculate.model;

import java.util.Arrays;

public enum TypeOperation {
    ADD("add"), DIV("div"), MUL("mul"), SUB("sub");

    private String value;

    TypeOperation(String value) {
        this.value = value;
    }

    public static TypeOperation fromValue(String value) {
        for (TypeOperation type : values()){
            if (type.value.equalsIgnoreCase(value)){
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown enum type " + value + " Allowed values: " + Arrays.toString(values()));
    }
}
