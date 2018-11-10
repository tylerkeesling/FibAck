package com.tylerkeesling;

/*
 *  Author: Tyler Keesling
 *  Project: #7 FibAck
 *  Date: 10 November 2018
 *  Class: Fibonacci
 *  Description: The Fibonacci class has 4 methods, 2 public and 2 private. This method will run the Fibonacci equation
 *               using no memoization and using memoization
 *  Limitations: The lookup table is limited to a length of 31
 */

public class Fibonacci {
  int tableAccesses = 0; // calls to table from optimizedFib
  int callsWithTable = 0; // count for fib
  int callsWithoutTable = 0; // count for optimizedFib
  int[] lookupTable = new int[31];

  // constructor
  public Fibonacci() {
    // prepopulate lookup table with base case, ignoring index 0;
    lookupTable[1] = 1;
    lookupTable[2] = 1;
  }

  /**
   * **********************************************************************************************
   */
  public int runFib(int n) {
    // System.out.println("Fibonacci stub");
    return fib(n);
  }

  private int fib(int n) {
    // increase count by 1 every time it is entered
    this.callsWithoutTable++;

    // base case; n is 1 or 2, return 1
    if (n <= 2) {
      return 1;
    }

    // recursive case
    return fib(n - 1) + fib(n - 2);
  }

  /**
   * **********************************************************************************************
   */
  public int runOptimizedFib(int n) {
    // System.out.println("Optimized Fibonacci stub");
    this.callsWithTable++; // count first call
    return optimizedFib(n);
  }

  private int optimizedFib(int n) {

    if (lookupTable[n] == 0) { // call to table

      lookupTable[n] = optimizedFib(n - 1) + optimizedFib(n - 2); // two method calls
      callsWithTable += 2; // count the two calls above

      tableAccesses += 3; // three total table calls
      return lookupTable[n]; // third table call
    } else {

      tableAccesses++;
      return lookupTable[n]; // call to table
    }
  }
}
