package com.tylerkeesling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {

  public static void runFibonacci() {
    try {
      File file = new File("Fibonacci Results.txt");
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);

      for (int i = 1; i < 31; i++) {
        Fibonacci fib = new Fibonacci();
        int result = fib.runFib(i);
        fib.runOptimizedFib(i);

        pw.println("fib(" + i + ") = " + result);
        pw.println("calls without table: " + fib.callsWithoutTable);
        pw.println("calls with table: " + fib.callsWithTable);
        pw.println("table look-ups: " + fib.tableAccesses + "\n");
      }

      pw.close();
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
    }
  }

  public static void runAckermann() {
    try {
      File file = new File("Ackermann Results.txt");
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);

      for (int x = 0; x < 4; x++) {
        for (int y = 0; y < 4; y++) {
          Ackermann ack = new Ackermann();
          int result = ack.runAck(x, y);
          ack.runOptimizedAck(x, y);

          pw.println("ack(" + x + ", " + y + ") = " + result);
          pw.println("calls without table: " + ack.callsWithoutTable);
          pw.println("calls with table: " + ack.callsWithTable);
          pw.println("table look-ups: " + ack.tableAccesses);
          pw.println("out of bounds: " + ack.outOfBounds);
          pw.println("y max: " + ack.yMax + "\n");
        }
      }

      pw.close();
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
    }
  }

  public static void main(String[] args) {
    //    runFibonacci();
    runAckermann();
  }
}
