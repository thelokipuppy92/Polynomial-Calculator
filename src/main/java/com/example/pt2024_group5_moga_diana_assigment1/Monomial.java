package com.example.pt2024_group5_moga_diana_assigment1;

class Monomial {
    private double coefficient;
    private int exponent;

    public Monomial(double coefficient, int exponent) {
        this.coefficient = coefficient;
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    @Override
    public String toString() {
        if (exponent == 0) {
            return Double.toString(coefficient);
        } else if (exponent == 1) {
            return coefficient + "x";
        } else {
            return coefficient + "x^" + exponent;
        }
    }
}

