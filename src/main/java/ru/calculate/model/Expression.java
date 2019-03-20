package ru.calculate.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data @NoArgsConstructor
public class Expression {

    @NotNull
    private double a;
    @NotNull
    private double b;
    private double result;

    public Expression(@NotNull double a, @NotNull double b) {
        this.a = a;
        this.b = b;
    }
}
