package com.company;

import com.sun.jdi.request.DuplicateRequestException;
import jdk.nashorn.api.tree.ForInLoopTree;

import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.function.DoubleBinaryOperator;

public class BruteforceCalculator
{
	private double constantC = 0;
	private double returnC = 0;
	private Random random = new Random();
	private final int ITERATIONS = 200_000;      //How many times to check for a given event. Bigger = slower, but more accurate
	private final int GRANULARITY = 100;        //How little to change C each iteration. Higher = smaller changes.
	private final double TOLERANCE = 10E-15;     //Tolerance for the chance being returned.
	private final int REPEATS = 25;             //How many times to repeat the process. Doesn't

	public double CalculateC(double target)
	{
		double hitCounter;
		int N = 1;
		double resultChance = 0;
		constantC = 0;
		returnC = 0;

		if (target == 1.0 || target == 0)
			return target;

		long startTime = System.currentTimeMillis();
		for (int i = 0; i < REPEATS; i++)
		{
			long innerStart = System.currentTimeMillis();
			while (Math.abs(resultChance - target) > TOLERANCE)
			{
				constantC -= (resultChance - target) / GRANULARITY;
				hitCounter = 0;
				for (int j = 0; j < ITERATIONS; j++)
					if (random.nextFloat() < constantC * N)
					{
						hitCounter += 1.0;
						N = 1;
					}
					else
						N++;
				resultChance = hitCounter / ITERATIONS;
			}
			returnC += constantC;
			resultChance = target-constantC;
			constantC = target;
			double innerDuration = (System.currentTimeMillis() - innerStart);
			System.out.println("Loop " + (i+1) + " out of " + REPEATS + " done. Time taken: " + innerDuration/1000 + " seconds.");
		}
		long endTime = System.currentTimeMillis();
		double duration = (endTime - startTime);
		System.out.println("Done calculating! Sorry for the wait. Time taken: " + duration/1000 + " seconds.");
		return returnC/REPEATS;
	}
}
