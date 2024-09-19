package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;

/**
 * QuickCalculator class processes command-line arguments for fraction calculations.
 * Supports operations such as addition, subtraction, multiplication, and division.
 *
 * Author: Slok Rajbhandari
 */
public class QuickCalculator {

  /**
   * Main method that processes command-line arguments for quick calculations.
   * 
   * @param args an array of expressions to evaluate
   */
  public static void main(String[] args) {
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();
    PrintWriter output = new PrintWriter(System.out, true);

    for (String expression : args) {
      try {
        processInput(expression, calculator, registerSet, output);
        output.println(expression + " -> " + calculator.get());
      } catch (Exception e) {
        output.println(expression + ": FAILED [" + e.getMessage() + "]");
      } // try
    } // for loop
  } // main(String[] args)

  /**
   * Processes the input expression and performs the corresponding operations.
   *
   * @param input the input expression to process
   * @param calculator the BFCalculator to use for calculations
   * @param registerSet the BFRegisterSet to use for storing/retrieving registers
   * @param output the PrintWriter for output
   */
  private static void processInput(
      String input, BFCalculator calculator, BFRegisterSet registerSet, PrintWriter output) {
    String[] tokens = input.split(" ");

    // Check for STORE command
    if (tokens[0].equalsIgnoreCase("STORE")) {
      if (tokens.length != 2 || tokens[1].length() != 1) {
        throw new IllegalArgumentException("Invalid STORE command");
      } // if loop
      char register = tokens[1].charAt(0);
      BigFraction lastValue = calculator.get();
      registerSet.store(register, lastValue);
      output.println("STORED");
      return;
    } // if loop

    BigFraction result;
    int i = 0;

    // Determine if the first token is a fraction or a register
    if (Character.isLetter(tokens[i].charAt(0))) {
      result = registerSet.get(tokens[i].charAt(0));
    } else {
      result = new BigFraction(tokens[i]);
    } // if loop

    i++;

    // Process the rest of the tokens (operators and values)
    while (i < tokens.length) {
      String operator = tokens[i];
      i++;

      BigFraction value;
      if (Character.isLetter(tokens[i].charAt(0))) {
        value = registerSet.get(tokens[i].charAt(0));
      } else {
        value = new BigFraction(tokens[i]);
      } // if loop

      // Perform operation based on operator
      switch (operator) {
        case "+":
          calculator.add(value);
          result = calculator.get();
          break;
        case "-":
          calculator.subtract(value);
          result = calculator.get();
          break;
        case "*":
          calculator.multiply(value);
          result = calculator.get();
          break;
        case "/":
          calculator.divide(value);
          result = calculator.get();
          break;
        default:
          throw new IllegalArgumentException("Invalid operator: " + operator);
      } // switch loop
      i++;
    } // while loop

    // Clear and set the final result in the calculator
    calculator.clear();
    calculator.add(result);
  } // processInput(inputs)
} // QuickCalculator class
