package com.tylerkeesling;

public class Fibonacci {
  int callsToTable = 0; // calls to table from optimizedFib
  int optimizedCount = 0; // count for fib
  int unoptomizedCount = 0; // count for optimizedFib
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
    this.unoptomizedCount++;

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
    return optimizedFib(n);
  }

  private int optimizedFib(int n) {
    this.optimizedCount++;

    if (this.lookupTable[n] == 0) {
      this.callsToTable++;
      this.lookupTable[n] = optimizedFib(n - 1) + optimizedFib(n - 2);
    } else {
      this.callsToTable++;
      return lookupTable[n];
    }

    // base case; n is 1 or 2, return 1
    if (n <= 2) {
      return 1;
    }

    // recursive case
    return optimizedFib(n - 1) + optimizedFib(n - 2);
  }
}
