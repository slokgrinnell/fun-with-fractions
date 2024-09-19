package edu.grinnell.csc207.util;

/**
 * BFRegisterSet class provides a set of registers ('a' through 'z')
 * for storing and retrieving BigFractions.
 *
 * Author: Slok Rajbhandari
 */
public class BFRegisterSet {
  private BigFraction[] registers;

  /**
   * Constructor for BFRegisterSet. Initializes all registers to zero.
   */
  public BFRegisterSet() {
    registers = new BigFraction[26];
    for (int i = 0; i < 26; i++) {
      registers[i] = new BigFraction("0");
    } // for(int)
  } // BFRegisterSet()

  /**
   * Stores the provided BigFraction in the specified register.
   * @param register the register to store the value in ('a' through 'z')
   * @param val the BigFraction to store
   */
  public void store(char register, BigFraction val) {
    int index = register - 'a';
    if (index >= 0 && index < 26) {
      registers[index] = val;
    } else {
      throw new IllegalArgumentException("Invalid register: " + register);
    } // if(index)
  } // store(char register, BigFraction val)

  /**
   * Retrieves the BigFraction stored in the specified register.
   * @param register the register to retrieve the value from ('a' through 'z')
   * @return the BigFraction stored in the register
   */
  public BigFraction get(char register) {
    int index = register - 'a';
    if (index >= 0 && index < 26) {
      return registers[index];
    } else {
      throw new IllegalArgumentException("Invalid register: " + register);
    } // if(index)
  } // get(char register)
} // BFRegisterSet class
