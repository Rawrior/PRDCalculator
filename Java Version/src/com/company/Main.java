package com.company;

import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args)
    {
        double ConstantC = 0;
//        Calculator calculator = new Calculator();
//        WildCalculator wildCalculator = new WildCalculator();
        BruteforceCalculator calculator = new BruteforceCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Welcome! Enter your desired percentage chance (without %): ");
        double desiredChance = scanner.nextDouble();
        //TODO: Add checks on bounds
        System.out.println("Thank you. Calculating...");

//        ConstantC = calculator.CalculateC(desiredChance);
        ConstantC = calculator.CalculateC(desiredChance/100);

        System.out.println("For your desired chance of " + desiredChance + "% use the (approximate) C value: " + ConstantC);
        System.out.println("Exiting");
    }
}
