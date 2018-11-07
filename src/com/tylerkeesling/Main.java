package com.tylerkeesling;

public class Main {

  public static void main(String[] args) {

    Fibonacci fib = new Fibonacci();

    int result = fib.runFib(30);
    System.out.println("fib result: " + result);
    System.out.println("fib count: " + fib.unoptomizedCount);
    
    int optResult = fib.runOptimizedFib(30);
    System.out.println("\noptFib result: " + optResult);
    System.out.println("optFib count: " + fib.optimizedCount);
    System.out.println("optFib table calls: " + fib.callsToTable);
  }
}
