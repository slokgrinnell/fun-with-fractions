package edu.grinnell.csc207.main;

import edu.grinnell.csc207.util.BigFraction;
import edu.grinnell.csc207.util.BFCalculator;
import edu.grinnell.csc207.util.BFRegisterSet;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * InteractiveCalculator class provides a REPL interface
 * for fraction calculations using BFCalculator.
 *
 * Author: Slok Rajbhandari
 */
public class InteractiveCalculator {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    BFCalculator calculator = new BFCalculator();
    BFRegisterSet registerSet = new BFRegisterSet();
    PrintWriter output = new PrintWriter(System.out, true);
    String input;

    while (true) {
      output.print("> ");
      output.flush();
      input = scanner.nextLine().trim();
      if (input.equalsIgnoreCase("QUIT")) {
        break;
      } // end of if loop check
      try {
        processInput(input, calculator, registerSet, output);
        output.println(calculator.get());
      } catch (Exception e) {
        output.println("*** ERROR [" + e.getMessage() + "] ***");
      } // end of try and catch
    } // main (String[] args)
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
    String[] tokens = input.split(" ");
    if (tokens[0].equalsIgnoreCase("STORE")) {
      if (tokens.length != 2 || tokens[1].length() != 1) {
        throw new IllegalArgumentException("Invalid STORE command");
      } // end of if loop
      char register = tokens[1].charAt(0);
      BigFraction lastValue = calculator.get();
      registerSet.store(register, lastValue);
      output.println("STORED");
      return;
    } // if loop

    BigFraction result;
    int i = 0;

    if (Character.isLetter(tokens[i].charAt(0))) {
      result = registerSet.get(tokens[i].charAt(0));
    } else {
      result = new BigFraction(tokens[i]);
    } // if loop

    i++;

    while (i < tokens.length) {
      String operator = tokens[i];
      i++;

      BigFraction value;
      if (Character.isLetter(tokens[i].charAt(0))) {
        value = registerSet.get(tokens[i].charAt(0));
      } else {
        value = new BigFraction(tokens[i]);
      } // if loop

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
      } // while loop
      i++;
    } // while loop

    calculator.clear();
    calculator.add(result);
  } // processInput(inputs)
} // InteractiveCalculator class
