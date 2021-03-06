package com.tylerkeesling;

/*
 *  Author: Tyler Keesling
 *  Project: #7 FibAck
 *  Date: 10 November 2018
 *  Class: Ackermann
 *  Description: The Ackermann class has 4 methods, 2 public and 2 private. This method will run the Ackermann equation
 *               using no memoization and using memoization
 *  Limitations: The lookup table is limited to a y or 101
 */

public class Ackermann {
  int yMax = 0; // max y that threw out of bounds
  int outOfBounds = 0; // number of out of bounds calls
  int[][] lookupTable; // declaration of lookup table that instantiates in the constructor
  int tableAccesses = 0; // calls to table from optimizedAck
  int callsWithTable = 0; // count for ack
  int callsWithoutTable = 0; // count for optimizedAck

  // constructor
  public Ackermann() {
    int xLength = 5; // no magic nums
    int yLength = 101;
    this.lookupTable = new int[xLength][yLength];
  }

  /**
   * **********************************************************************************************
   */
  public int runAck(int x, int y) {
    // System.out.println("Ackermann stub");
    callsWithoutTable++;
    return ack(x, y);
  }

  private int ack(int x, int y) {
    if (y > yMax) { // check incoming y and compare value to yMax
      yMax = y; // set yMax to the new y
    }

    if (x == 0) { // base case where x is equal to 0
      return y + 1;

    } else if (y == 0) {
      callsWithoutTable++;
      return ack(x - 1, 1);

    } else {
      callsWithoutTable += 2;
      return ack(x - 1, ack(x, y - 1));
    }
  }

  /**
   * **********************************************************************************************
   */
  public int runOptimizedAck(int x, int y) {
    // System.out.println("Optimized Ackermann stub");
    this.callsWithTable++; // count first call
    return optimizedAck(x, y);
  }

  private int optimizedAck(int x, int y) throws StackOverflowError {
    if (y > yMax) { // check incoming y and compare value to yMax
      yMax = y; // set yMax to the new y
    }

    // testing if y is IndexOutOfBounds for the lookup table
    if (y > lookupTable[0].length - 1) {
      tableAccesses++; // count table access above
      outOfBounds++; // count y going out of bounds
      if (x == 0) { // base case where x is equal to 0
        return y + 1;
      } else {
        callsWithTable += 2;
        return optimizedAck(x - 1, optimizedAck(x, y - 1));
      }
    }

    if (lookupTable[x][y] == 0) { // if doesnt exist in table
      tableAccesses++; // count the table access above

      if (x == 0) { // base case where x is equal to 0
        tableAccesses++;
        lookupTable[x][y] = y + 1;
        return y + 1;

      } else if (y == 0) {
        callsWithTable++;
        tableAccesses++;
        int result = optimizedAck(x - 1, 1);
        lookupTable[x][y] = result;
        return result;

      } else {
        callsWithTable += 2;
        tableAccesses++;
        int result = optimizedAck(x - 1, optimizedAck(x, y - 1));
        lookupTable[x][y] = result;
        return result;
      }
    }

    tableAccesses++;
    return lookupTable[x][y];
  }
}
