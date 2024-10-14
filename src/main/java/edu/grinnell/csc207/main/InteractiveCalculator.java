package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * InteractiveCalculator class provides a REPL interface
 * for fraction calculations using BFCalculator and BFRegisterSet.
 *
 * Author: Slok Rajbhandari
 */
public class InteractiveCalculator {

  /**
   * Main method for the InteractiveCalculator. This method provides a
   * REPL (Read-Eval-Print Loop) interface, allowing users to input fraction
   * calculations interactively until the "QUIT" command is entered.
   * It continuously reads user input from the console, processes the input
   * using BFCalculator and BFRegisterSet, and outputs the result to the console.
   *
   * @param args Command-line arguments, not used in this program.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();
    PrintWriter output = new PrintWriter(System.out, true);
    String input;

    // REPL loop for interactive input
    while (true) {
      output.print("> ");
      output.flush();
      input = scanner.nextLine().trim();
      if (input.equalsIgnoreCase("QUIT")) {
        break;
      } // Exit if user types QUIT
      try {
        processInput(input, calculator, registerSet, output);
        output.println(calculator.get());
      } catch (Exception e) {
        output.println("*** ERROR: An unexpected error occurred. ***");
      } // end of if loop
    } // end of while
    scanner.close();
  } // main(String[] args)

  /**
   * Processes the input command and performs the corresponding operation.
   *
   * @param input the input command
   * @param calculator the BFCalculator to use
   * @param registerSet the BFRegisterSet to use
   * @param output the PrintWriter for output
   */
  private static void processInput(
      String input, BFCalculator calculator, BFRegisterSet registerSet, PrintWriter output) {

    // Split the input into tokens (space-separated)
    String[] tokens = input.split(" ");
    if (tokens.length == 0 || tokens[0].isEmpty()) {
      output.println("*** ERROR: Invalid expression ***");
      return;
    } // end of if

    // Handle the STORE command
    if (tokens[0].equalsIgnoreCase("STORE")) {
      if (tokens.length != 2 || tokens[1].length() != 1) {
        output.println("*** ERROR: Invalid STORE command ***");
        return;
      } // end of if
      char register = tokens[1].charAt(0);
      BigFraction lastValue = calculator.get();
      registerSet.store(register, lastValue);
      output.println("STORED");
      return;
    } // end of if

    // Parse and compute the expression
    BigFraction result = parseValue(tokens[0], registerSet);

    // If the input is just a single fraction or register
    if (tokens.length == 1) {
      calculator.clear();
      calculator.add(result);
      return;
    } // end of if

    // Process the rest of the tokens
    int i = 1;
    while (i < tokens.length) {
      String operator = tokens[i];
      i++;

      if (i >= tokens.length) {
        output.println("*** ERROR: Invalid expression, missing value after operator ***");
        return;
      } // end of if

      BigFraction value = parseValue(tokens[i], registerSet);
      result = applyOperator(result, value, operator, output);
      i++;
    } // end of while

    // Update calculator with the final result
    calculator.clear();
    calculator.add(result);
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
    } // end of if
  } // parseValue

  /**
   * Applies the operator between two BigFraction values.
   *
   * @param result the current result of the calculation
   * @param value the next value to apply the operator to
   * @param operator the operator in the expression (+, -, *, /)
   * @return the new result after applying the operator
   * @param output the error message
   */
  private static BigFraction applyOperator(BigFraction result, BigFraction
      value, String operator, PrintWriter output) {
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
        output.println("*** ERROR: Invalid operator '" + operator + "' ***");
        return result;
    } // end of switch
  } // applyOperator
} // InteractiveCalculator class
