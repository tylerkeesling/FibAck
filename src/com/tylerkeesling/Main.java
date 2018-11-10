package com.tylerkeesling;

/*
 *  Author: Tyler Keesling
 *  Project: #7 FibAck
 *  Date: 10 November 2018
 *  Class: Main
 *  Description: This Main class is the driver for the application. It runs both Fib and Ack equations and will
 *               print the designated values into a file
 */

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

  public static void runFibonacci() {
    try {
      // setup to write to a file
      File file = new File("Fibonacci Results.txt");
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);

      // print header
      pw.println("Authored by Tyler Keesling\n");
      pw.println("Fibonacci Results\n");

      for (int i = 1; i < 31; i++) {
        Fibonacci fib = new Fibonacci();
        int result = fib.runOptimizedFib(i);
        fib.runFib(i);

        pw.println("fib(" + i + ") = " + result);
        pw.println("calls without table: " + fib.callsWithoutTable);
        pw.println("calls with table: " + fib.callsWithTable);
        pw.println("table accesses: " + fib.tableAccesses + "\n");
      }

      pw.close();

      // Commented out code for interactive
      //      Scanner sc = new Scanner(System.in);
      //      System.out.println("Fibonacci number: ");
      //      int fibNumber = sc.nextInt();
      //      Fibonacci fib = new Fibonacci();
      //      fib.runFib(fibNumber);
      //      fib.runOptimizedFib(fibNumber);

    } catch (IOException e) {
      System.out.println(e.getStackTrace());
    }
  }

  public static void runAckermann() {
    try {
      // setup to write to a file
      File file = new File("Ackermann Results.txt");
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);

      // print header
      pw.println("Authored by Tyler Keesling\n");
      pw.println("Ackermann Results\n");

      for (int x = 0; x < 5; x++) {
        for (int y = 0; y < 16; y++) {

          try {
            Ackermann ack = new Ackermann();
            int result = ack.runOptimizedAck(x, y);
            ack.runAck(x, y);

            pw.println("ack(" + x + ", " + y + ") = " + result);
            pw.println("calls without table: " + ack.callsWithoutTable);
            pw.println("calls with table: " + ack.callsWithTable);
            pw.println("table accesses: " + ack.tableAccesses);
            pw.println("out of bounds: " + ack.outOfBounds);
            pw.println("y max: " + ack.yMax + "\n");
          } catch (StackOverflowError e) {
            for (int z = y; z < 16; z++) {
              pw.println("ack(" + x + ", " + z + ") could not be computed\n");
            }
            break;
          }
        }
      }

      pw.close();

      // Commented out code for interactive
      //      Scanner sc = new Scanner(System.in);
      //      System.out.println("x: ");
      //      int x = sc.nextInt();
      //      System.out.println("y: ");
      //      int y = sc.nextInt();
      //      Ackermann ack = new Ackermann();
      //      ack.runAck(x, y);
      //      Ack.runOptimizedAck(x, y);

    } catch (IOException e) {
      System.out.println(e.getStackTrace());
    }
  }

  public static void fibTime() {
    long startTime, endTime;

    System.out.println("Fibonacci");

    startTime = System.nanoTime();
    for (int i = 1; i < 31; i++) {
      Fibonacci fib = new Fibonacci();
      fib.runFib(i);
    }
    endTime = System.nanoTime();

    System.out.println("Regular: " + (endTime - startTime) + " ns");

    startTime = System.nanoTime();
    for (int i = 1; i < 31; i++) {
      Fibonacci fib = new Fibonacci();
      fib.runOptimizedFib(i);
    }
    endTime = System.nanoTime();

    System.out.println("Opt: " + (endTime - startTime) + " ns");
  }

  public static void ackTime() {
    long startTime, endTime;

    System.out.println("\nAckermann: ");

    startTime = System.currentTimeMillis();
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < 16; y++) {
        try {
          Ackermann ack = new Ackermann();
          ack.runAck(x, y);
        } catch (StackOverflowError e) {
          break;
        }
      }
    }
    endTime = System.currentTimeMillis();

    System.out.println("Regular: " + (endTime - startTime) + " ms");

    startTime = System.currentTimeMillis();
    for (int x = 0; x < 5; x++) {
      for (int y = 0; y < 16; y++) {
        try {
          Ackermann ack = new Ackermann();
          ack.runOptimizedAck(x, y);
        } catch (StackOverflowError e) {
          break;
        }
      }
    }
    endTime = System.currentTimeMillis();

    System.out.println("Opt: " + (endTime - startTime) + " ms");
  }

  public static void main(String[] args) {
    // runFibonacci();
    // runAckermann();
    fibTime();
    ackTime();
  }
}
