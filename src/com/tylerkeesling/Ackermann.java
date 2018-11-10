package com.tylerkeesling;

public class Ackermann {
  int yMax = 0; // max y that threw out of bounds
  int outOfBounds = 0; // number of out of bounds calls
  int tableAccesses = 0; // calls to table from optimizedAck
  int callsWithTable = 0; // count for ack
  int callsWithoutTable = 0; // count for optimizedAck
  int[][] lookupTable = new int[5][101];

  // constructor
  public Ackermann() {}

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

    if (lookupTable[x][y] == 0) {
      tableAccesses++; // count the table access above

      if (x == 0) { // base case where x is equal to 0
        tableAccesses++;
        lookupTable[x][y] = y + 1;
        return lookupTable[x][y];

      } else if (y == 0) {
        callsWithTable++;
        tableAccesses++;
        lookupTable[x][y] = optimizedAck(x - 1, 1);
        return lookupTable[x][y];

      } else {
        callsWithTable += 2;
        tableAccesses++;
        lookupTable[x][y] = optimizedAck(x - 1, optimizedAck(x, y - 1));
        return lookupTable[x][y];
      }
    }

    tableAccesses++;
    return lookupTable[x][y];
  }
}
