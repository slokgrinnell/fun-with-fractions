# Fun with Fractions

## Overview

This project implements a fraction-based calculator with interactive and quick modes. It supports basic arithmetic operations on fractions, stores values in registers, and handles input via both REPL and command-line arguments.

## Classes

1. **BigFraction**: Handles fractions with `BigInteger` for precision and supports operations like addition, subtraction, multiplication, and division.
2. **BFCalculator**: A calculator that stores the last computed value and performs operations on fractions.
3. **BFRegisterSet**: Manages 26 registers ('a' through 'z') for storing fractions.
4. **InteractiveCalculator**: A REPL interface for interacting with the calculator.
5. **QuickCalculator**: A command-line interface for quick fraction calculations.

## Authors - Slok Rajbhandari
## Acknowledgements - https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Fa/mps/mp02.html

## Github link - https://github.com/slokgrinnell/fun-with-fractions

Throw new and try and catch I learned from youtube because they made a project with the try and catch.

WHAT I FIXED:

- I fixed the output and used booleans to check if there is an error or not. If there is an error in the interactive calculator I made boolean false so that it does not return any values. I also fixed 2 x 3 error showing output
-Everything matches the script now


SECOND REDO FIXES

- InteractiveCalculator.java for lines 46 & 111: "Regexp: Line matches the illegal pattern 'end brace without comment'."

FIXED !!!