package edu.grinnell.csc207.util;

import java.math.BigInteger;

/**
 * BigFraction class that represents a fraction using BigInteger for high precision.
 * It supports operations like addition, subtraction, multiplication, and division.
 * All fractions are stored in their simplest form.
 * Author: Slok Rajbhandari
 */
public class BigFraction {
  private BigInteger numerator;
  private BigInteger denominator;

  /**
   * Constructor for BigFraction that initializes the fraction with given numerator and denominator.
   *
   * @param num the numerator of the fraction
   * @param den the denominator of the fraction
   */
  public BigFraction(BigInteger num, BigInteger den) {
    if (den.equals(BigInteger.ZERO)) {
      throw new ArithmeticException("Denominator cannot be zero");
    } // end of if(den)
    this.numerator = num;
    this.denominator = den;
    reduce(); // Simplify the fraction upon creation
  } // BigFraction(BigInteger num, BigInteger den)

  /**
   * Constructor for BigFraction that initializes
   * fraction with integer numerator and denominator.
   *
   * @param num the numerator of the fraction
   * @param den the denominator of the fraction
   */
  public BigFraction(int num, int den) {
    this(BigInteger.valueOf(num), BigInteger.valueOf(den));
  } // BigFraction(int num, int den)

  /**
   * Constructor for BigFraction from a string input (e.g., "3/4" or "5").
   *
   * @param fraction the string representation of the fraction
   */
  public BigFraction(String fraction) {
    String[] parts = fraction.split("/");
    if (parts.length == 2) {
      this.numerator = new BigInteger(parts[0]);
      this.denominator = new BigInteger(parts[1]);
    } else {
      this.numerator = new BigInteger(parts[0]);
      this.denominator = BigInteger.ONE;
    } // if loop (parts.length)
    reduce();
  } // BigFraction(String fraction)

  /**
   * Returns the numerator of the fraction.
   *
   * @return the numerator as a BigInteger
   */
  public BigInteger numerator() {
    return numerator;
  } // numerator()

  /**
   * Returns the denominator of the fraction.
   *
   * @return the denominator as a BigInteger
   */
  public BigInteger denominator() {
    return denominator;
  } // denominator()

  /**
   * Returns the numerator.
   *
   * @return the numerator as a BigInteger
   */
  public BigInteger getNumerator() {
    return this.numerator;
  } // getNumerator()

  /**
   * Returns the denominator.
   *
   * @return the denominator as a BigInteger
   */
  public BigInteger getDenominator() {
    return this.denominator;
  } // getDenominator()

  /**
   * Adds two BigFraction values and returns the result.
   *
   * @param other the other BigFraction to add
   * @return the result of addition
   */
  public BigFraction add(BigFraction other) {
    BigInteger num = this.numerator.multiply(other.denominator)
        .add(other.numerator.multiply(this.denominator));
    BigInteger den = this.denominator.multiply(other.denominator);
    return new BigFraction(num, den);
  } // add(BigFraction other)

  /**
   * Subtracts another BigFraction from this one.
   *
   * @param other the other BigFraction to subtract
   * @return the result of subtraction
   */
  public BigFraction subtract(BigFraction other) {
    BigInteger num = this.numerator.multiply(other.denominator)
        .subtract(other.numerator.multiply(this.denominator));
    BigInteger den = this.denominator.multiply(other.denominator);
    return new BigFraction(num, den);
  } // subtract(BigFraction other)

  /**
   * Multiplies two BigFraction values and returns the result.
   *
   * @param other the other BigFraction to multiply
   * @return the result of multiplication
   */
  public BigFraction multiply(BigFraction other) {
    return new BigFraction(this.numerator.multiply(other.numerator),
        this.denominator.multiply(other.denominator));
  } // multiply(BigFraction other)

  /**
   * Divides this BigFraction by another and returns the result.
   *
   * @param other the other BigFraction to divide by
   * @return the result of division
   */
  public BigFraction divide(BigFraction other) {
    return new BigFraction(this.numerator.multiply(other.denominator),
        this.denominator.multiply(other.numerator));
  } // divide(BigFraction other)

  /**
   * Reduces the fraction to its simplest form.
   */
  private void reduce() {
    BigInteger gcd = numerator.gcd(denominator);
    numerator = numerator.divide(gcd);
    denominator = denominator.divide(gcd);
    if (denominator.signum() == -1) {
      numerator = numerator.negate();
      denominator = denominator.negate();
    } // if loop (denominator)
  } // reduce()

  /**
   * Returns the string representation of the BigFraction.
   *
   * @return the string representation of the fraction
   */
  @Override
  public String toString() {
    if (denominator.equals(BigInteger.ONE)) {
      return numerator.toString();
    } else {
      return numerator + "/" + denominator;
    } // if loop (denominator)
  } // toString()
} // BigFraction class
