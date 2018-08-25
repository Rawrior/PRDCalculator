package com.company;

import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

public class Main {

    public static void main(String[] args)
    {
        double ConstantC = 0;
        Calculator calculator = new Calculator();
//        WildCalculator wildCalculator = new WildCalculator();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome! Enter your desired chance (between 0 and 1):");
        double desiredChance = scanner.nextDouble();
        System.out.println("Thank you. Calculating...");

        ConstantC = calculator.CalculateC(desiredChance);

        System.out.println("Done calculating!\nFor your desired chance of " + desiredChance + " use the C value: " + ConstantC);
        System.out.println("Exiting");
    }
}
