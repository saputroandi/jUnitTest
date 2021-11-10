package belajar_unit_test;

public class Calculator {

    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    public Integer divide(Integer first, Integer second) {

        if (second != 0) return first / second;

        throw new IllegalArgumentException("Can not divide by zero");

    }

}
