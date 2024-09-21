package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;

/**
 * QuickCalculator class allows processing mathematical expressions
 * involving fractions using BFCalculator and BFRegisterSet.
 *
 * Author: Mihika
 */
public class QuickCalculator {

/**
 * Main method for the QuickCalculator. It processes command-line
 * arguments as fraction calculations and outputs the results.
 * Invalid expressions are handled and reported.
 *
 * @param args Command-line arguments representing expressions to calculate.
 */
  public static void main(String[] args) {
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();
    PrintWriter output = new PrintWriter(System.out, true);

    for (String expression : args) {
      try {
        processInput(expression, calculator, registerSet, output);
      } catch (Exception e) {
        output.println(expression + ": FAILED [" + e.getMessage() + "]");
      } // end of try-catch
    } // end of for loop
  } // main(String[] args)

  /**
   * Processes a single input expression and performs the corresponding
   * operation based on the tokens extracted.
   *
   * @param input the input expression
   * @param calculator the BFCalculator to use for computations
   * @param registerSet the BFRegisterSet to store or retrieve values
   * @param output the PrintWriter to output results
   */
  private static void processInput(
      String input, BFCalculator calculator, BFRegisterSet registerSet, PrintWriter output) {

    // Split the input by spaces for parsing tokens
    String[] tokens = input.split(" ");
    if (tokens.length == 0 || tokens[0].isEmpty()) {
      output.println("Invalid expression");
    } // end of if

    // Handle the STORE command
    if (tokens[0].equalsIgnoreCase("STORE")) {
      if (tokens.length != 2 || tokens[1].length() != 1) {
        output.println("Invalid STORE command");
      } // end of if
      char register = tokens[1].charAt(0);
      BigFraction lastValue = calculator.get();
      registerSet.store(register, lastValue);
      output.println("STORED");
      return;
    } // end of if

    // Parse and compute the expression
    BigFraction result = parseValue(tokens[0], registerSet);

    int i = 1;
    while (i < tokens.length) {
      String operator = tokens[i];
      i++;

      if (i >= tokens.length) {
        output.println("Invalid expression");
      } // end of if

      BigFraction value = parseValue(tokens[i], registerSet);
      result = applyOperator(result, value, operator, calculator, output);
      i++;
    } // end of while

    calculator.clear();
    calculator.add(result);
    output.println(input + " -> " + result);
  } // processInput

  /**
   * Parses a token to return either a stored value from the register set
   * or a new BigFraction object based on the token.
   *
   * @param token the string representing the value or register
   * @param registerSet the BFRegisterSet to retrieve values from
   * @return the BigFraction corresponding to the token
   */
  private static BigFraction parseValue(String token, BFRegisterSet registerSet) {
    if (Character.isLetter(token.charAt(0))) {
      return registerSet.get(token.charAt(0));
    } else {
      return new BigFraction(token);
    } // end of if-else
  } // parseValue

  /**
   * Applies the operator between two BigFraction values.
   *
   * @param result the current result of the calculation
   * @param value the next value to apply the operator to
   * @param operator the operator in the expression (+, -, *, /)
   * @param calculator the BFCalculator to use for the operation
   * @param output for illegal output
   * @return the new result after applying the operator
   */
  private static BigFraction applyOperator(BigFraction result, BigFraction
      value, String operator, BFCalculator calculator, PrintWriter output) {
    switch (operator) {
      case "+":
        return result.add(value);
      case "-":
        return result.subtract(value);
      case "*":
        return result.multiply(value);
      case "/":
        return result.divide(value);
      default:
        output.println("Invalid operator: " + operator);
        return result;
    } // end of switch
  } // applyOperator
} // QuickCalculator class
