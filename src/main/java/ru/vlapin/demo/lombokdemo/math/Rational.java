package ru.vlapin.demo.lombokdemo.math;

import java.util.function.LongBinaryOperator;

public record Rational(
        long numerator,
        long denominator) {

    private static final LongBinaryOperator GCD =
            (a, b) -> b == 0 ? a : Rational.GCD.applyAsLong(b, a % b);

    public Rational(long numerator, long denominator) {
        if (denominator == 0)
            throw new AssertionError("denominator is zero");

        var gcd = GCD.applyAsLong(Math.abs(numerator), Math.abs(denominator));

        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public Rational(long numerator) {
        this(numerator, 1);
    }

    @Override
    public String toString() {
        return "%d/%d".formatted(numerator, denominator);
    }

    public Rational add(Rational that) {
        return new Rational(numerator * that.denominator + that.numerator * denominator,
                            denominator * that.denominator);
    }

    public Rational add(long value) {
        return new Rational(numerator + value * denominator, denominator);
    }

    public boolean lessThen(long value) {
        return numerator < value * denominator;
    }

    public boolean lessThen(Rational that) {
        return numerator * that.denominator < that.numerator * denominator;
    }

    public Rational max(Rational that) {
        return lessThen(that) ? that : this;
    }

    public Rational subtract(Rational that) {
        return new Rational(numerator * that.denominator - that.numerator * denominator,
                            denominator * that.denominator);
    }

    public Rational multiply(Rational that) {
        return new Rational(numerator * that.numerator,
                            denominator * that.denominator);
    }

    public Rational swap() {
        return new Rational(denominator, numerator);
    }

    public Rational divide(Rational that) {
        return multiply(that.swap());
    }

    public double toDoubleValue() {
        return (double) numerator / denominator;
    }
}
