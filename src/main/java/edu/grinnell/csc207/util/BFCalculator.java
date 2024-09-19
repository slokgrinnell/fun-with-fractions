package edu.grinnell.csc207.util;

/**
 * BFCalculator class simulates a basic calculator that operates on BigFractions.
 * It stores the last computed value and provides methods for arithmetic operations.
 *
 * Author: Slok Rajbhandari
 */
public class BFCalculator {
  private BigFraction lastValue;

  /**
   * Constructor for BFCalculator. Initializes the lastValue to zero.
   */
  public BFCalculator() {
    lastValue = new BigFraction("0");
  } // BFCalculator()

  /**
   * Gets the last computed value from the calculator.
   *
   * @return the last computed BigFraction
   */
  public BigFraction get() {
    return lastValue;
  } // get()

  /**
   * Adds the provided BigFraction to the last computed value.
   *
   * @param val the BigFraction to add
   */
  public void add(BigFraction val) {
    lastValue = lastValue.add(val);
  } // add(BigFraction val)

  /**
   * Subtracts the provided BigFraction from the last computed value.
   *
   * @param val the BigFraction to subtract
   */
  public void subtract(BigFraction val) {
    lastValue = lastValue.subtract(val);
  } // subtract(BigFraction val)

  /**
   * Multiplies the last computed value by the provided BigFraction.
   *
   * @param val the BigFraction to multiply
   */
  public void multiply(BigFraction val) {
    lastValue = lastValue.multiply(val);
  } // multiply(BigFraction val)

  /**
   * Divides the last computed value by the provided BigFraction.
   *
   * @param val the BigFraction to divide by
   */
  public void divide(BigFraction val) {
    lastValue = lastValue.divide(val);
  } // divide(BigFraction val)

  /**
   * Clears the calculator by resetting the last computed value to zero.
   */
  public void clear() {
    lastValue = new BigFraction("0");
  } // clear()
} // BFCalculator class
