package com.tylerkeesling;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {



  public static void main(String[] args) {
  
    try {
      File file = new File("Fibonacci Results");
      FileWriter fw = new FileWriter(file);
      PrintWriter pw = new PrintWriter(fw);
    } catch (IOException e) {
      System.out.println(e.getStackTrace());
    }

    
  }
  
  public static void runFibonacci() {
    Fibonacci fib = new Fibonacci();
    
    for (int i = 1; i < 31; i++) {
    
      int result = fib.runFib(i);
      System.out.println("fib result: " + result);
      System.out.println("fib count: " + fib.unoptomizedCount);
    
      int optResult = fib.runOptimizedFib(i);
      System.out.println("\noptFib result: " + optResult);
      System.out.println("optFib count: " + fib.optimizedCount);
      System.out.println("optFib table calls: " + fib.callsToTable + "\n");
    
    }
  }
}
