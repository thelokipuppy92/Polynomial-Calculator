package com.example.pt2024_group5_moga_diana_assigment1;


import java.util.*;

class Polynomial {
    private final Map<Integer, Monomial> terms;

    public Polynomial() {
        this.terms = new HashMap<>();
    }

    public void addTerm(Monomial monomial) {
        terms.put(monomial.getExponent(), monomial);
    }


    public Polynomial add(Polynomial other) {
        Polynomial result = new Polynomial();

        // Add terms from this polynomial
        for (Monomial monomial : terms.values()) {
            result.addTerm(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        // Add terms from the other polynomial
        for (Monomial monomial : other.terms.values()) {
            Monomial existingMonomial = result.terms.getOrDefault(monomial.getExponent(), null);
            if (existingMonomial != null) {
                double newCoefficient = existingMonomial.getCoefficient() + monomial.getCoefficient();
                result.addTerm(new Monomial(newCoefficient, monomial.getExponent()));
            } else {
                result.addTerm(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
            }
        }
        // Debug
        System.out.println("Added Polynomial: " + result);

        return result;
    }

    public Polynomial subtract(Polynomial other) {
        Polynomial result = new Polynomial();

        // Add terms from this polynomial
        for (Monomial monomial : terms.values()) {
            result.addTerm(new Monomial(monomial.getCoefficient(), monomial.getExponent()));
        }

        // Subtract terms from the other polynomial
        for (Monomial monomial : other.terms.values()) {
            Monomial existingMonomial = result.terms.getOrDefault(monomial.getExponent(), null);
            if (existingMonomial != null) {
                double newCoefficient = existingMonomial.getCoefficient() - monomial.getCoefficient();
                result.addTerm(new Monomial(newCoefficient, monomial.getExponent()));
            } else {
                result.addTerm(new Monomial(-monomial.getCoefficient(), monomial.getExponent()));
            }
        }
        // Debug
        System.out.println("Subtracted Polynomial: " + result);

        return result;
    }


    public Polynomial multiply(Polynomial other) {
        Polynomial result = new Polynomial();

        for (Monomial thisMonomial : terms.values()) {
            for (Monomial otherMonomial : other.terms.values()) {
                double newCoefficient = thisMonomial.getCoefficient() * otherMonomial.getCoefficient();
                int newExponent = thisMonomial.getExponent() + otherMonomial.getExponent();

                Monomial multipliedMonomial = new Monomial(newCoefficient, newExponent);
                Monomial existingMonomial = result.terms.getOrDefault(newExponent, null);

                if (existingMonomial != null) {
                    double sumCoefficients = existingMonomial.getCoefficient() + multipliedMonomial.getCoefficient();
                    if (sumCoefficients != 0) {
                        result.addTerm(new Monomial(sumCoefficients, newExponent));
                    } else {
                        result.terms.remove(newExponent); // Remove term if coefficient becomes zero
                    }
                } else {
                    result.addTerm(multipliedMonomial);
                }
            }
        }
        // Debug
        System.out.println("Multiplied Polynomial: " + result);

        return result;
    }


    public Polynomial derivative() {
        Polynomial result = new Polynomial();

        for (Monomial monomial : terms.values()) {
            double coefficient = monomial.getCoefficient();
            int exponent = monomial.getExponent();

            if (exponent > 0) {
                double derivativeCoefficient = coefficient * exponent;
                int derivativeExponent = exponent - 1;
                result.addTerm(new Monomial(derivativeCoefficient, derivativeExponent));
            }
        }
        // Debug
        System.out.println("Derivative Polynomial: " + result);

        return result;
    }

    public Polynomial integrate() {
        Polynomial result = new Polynomial();

        for (Monomial monomial : terms.values()) {
            double coefficient = monomial.getCoefficient();
            int exponent = monomial.getExponent();

            double integralCoefficient = coefficient / (exponent + 1);
            int integralExponent = exponent + 1;

            result.addTerm(new Monomial(integralCoefficient, integralExponent));
        }

        result.addTerm(new Monomial(0, 0));

        // Debug
        System.out.println("Indefinite Integral Polynomial: " + result);

        return result;
    }



    public static Polynomial parsePolynomial(String polynomialString) {
        Polynomial polynomial = new Polynomial();
        polynomialString = preparePolynomialString(polynomialString);
        String[] termStrings = splitIntoTermStrings(polynomialString);

        for (String term : termStrings) {
            if (!term.isEmpty()) {
                parseAndAddTerm(term, polynomial);
            }
        }
        // Debug
        System.out.println("Parsed Polynomial: " + polynomial);

        return polynomial;
    }

    private static String preparePolynomialString(String polynomialString) {
        return polynomialString.replaceAll("\\s", "").toLowerCase();
    }

    private static String[] splitIntoTermStrings(String polynomialString) {
        return polynomialString.split("(?=[-+])");
    }

    private static void parseAndAddTerm(String term, Polynomial polynomial) {
        double coefficient = 0;
        int exponent = 0;
        String[] parts = term.split("x\\^?");

        if (parts.length == 2) {
            coefficient = parseCoefficient(parts[0]);
            exponent = parseExponent(parts[1]);
        } else if (parts.length == 1 && parts[0].contains("x")) {
            coefficient = parseCoefficient(parts[0].replace("x", ""));
            exponent = 1;
        } else if (parts.length == 1) {
            coefficient = parseCoefficient(parts[0]);
        } else {
            handleInvalidInput(term);
            return;
        }

        polynomial.addTerm(new Monomial(coefficient, exponent));
    }

    private static double parseCoefficient(String coefficientString) {
        return coefficientString.isEmpty() ? 1 : Double.parseDouble(coefficientString);
    }

    private static int parseExponent(String exponentString) {
        return Integer.parseInt(exponentString);
    }

    private static void handleInvalidInput(String term) {
        System.err.println("Invalid input format for term: " + term);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<Integer> sortedExponents = new ArrayList<>(terms.keySet());
        Collections.sort(sortedExponents, Collections.reverseOrder());
        boolean isFirstTerm = true;
        for (Integer exponent : sortedExponents) {
            Monomial monomial = terms.get(exponent);
            double coefficient = monomial.getCoefficient();
            if (coefficient != 0) {
                if (isFirstTerm) {
                    isFirstTerm = false;
                } else {
                    sb.append(coefficient < 0 ? " - " : " + ");
                }

                coefficient = Math.abs(coefficient);
                if (coefficient != 1 || exponent == 0) {
                    sb.append(coefficient);
                }
                if (exponent > 0) {
                    sb.append("x");
                    if (exponent > 1) {
                        sb.append("^").append(exponent);
                    }
                }
            }
        }

        if (sb.length() == 0) {
            sb.append("0");
        }

        return sb.toString();
    }


}