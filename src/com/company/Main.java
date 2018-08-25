package com.company;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
    {
        double ConstantC = 0;
        BruteforceCalculator calculator = new BruteforceCalculator();
        Scanner scanner = new Scanner(System.in);
        String inputString;
        double desiredChance = 0;
        DecimalFormat df = new DecimalFormat("0.00000");
        df.setRoundingMode(RoundingMode.DOWN);

        System.out.println("Welcome! Enter your desired percentage chance (without %). Alternatively, enter \"exit\" to close the program.");
        do
        {
            inputString = scanner.nextLine();
            //TODO: Add checks on bounds
            if (isNumeric(inputString))
            {
                System.out.println("Thank you. Calculating...");
                desiredChance = Double.parseDouble(inputString);
                ConstantC = calculator.CalculateC(desiredChance / 100);
                System.out.println("For your desired chance of " + desiredChance + "% use the (approximate) C value: " + df.format(ConstantC));
                System.out.println("Enter a new number to recalculate, or \"exit\" to exit.");
            }
        } while (!inputString.toLowerCase().equals("exit"));
        System.out.println("Exiting");
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }
}
