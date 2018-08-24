package com.company;

import Jama.Matrix;
import Jama.EigenvalueDecomposition;

public class Calculator
{
	private double probability = 0.5;
	private double ConstantC = 0.30210;
	private Matrix matrix;

	public double CalculateC(double target)
	{
		try
		{
			ConstantC = adjustC(ConstantC, target, probability);
		}
		catch (Exception e)
		{
			System.out.println("Something went wrong in adjustC. Exception follows.");
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try
		{
			matrix = createMatrix(matrix, ConstantC);
		}
		catch (Exception e)
		{
			System.out.println("Something went wrong in createMatrix. Exception follows.");
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		try
		{
			ConstantC = getEigenvector(matrix);
		}
		catch (Exception e)
		{
			System.out.println("Something went wrong in getEigenvector. Exception follows.");
			System.out.println(e.getClass().getSimpleName());
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return ConstantC;
	}

	private double adjustC(double constantC, double target, double probability)
	{
		System.out.println("Adjusting C value...");
		while (Math.abs(target - probability) > 10e-8)
		{
			if (ConstantC < 0.1)
				ConstantC += (target - probability)/8;
			else
				ConstantC += (target - probability)/2;
		}

		System.out.println("Done\n");
		return constantC;
	}

	private Matrix createMatrix(Matrix matrix, double constantC)
	{
		System.out.println("Creating matrix...");
		matrix = new Matrix((int)Math.ceil(1/constantC), (int)Math.ceil(1/constantC));

		System.out.println("Populating...");
		for (int n = 0; n < matrix.getRowDimension(); n++)
			if (n*constantC < 1)
				matrix.set(n, 1, n*constantC);
			else
				matrix.set(n, 1, 1);

		for (int n = 0; n < matrix.getRowDimension() - 1; n++)
			matrix.set(n,n+1, 1-matrix.get(n, 1));

		System.out.println("Done\n");
		return matrix;
	}

	private double getEigenvector(Matrix matrix)
	{
		System.out.println("Setting up eigenmatrix...");
		double probability;
		EigenvalueDecomposition decomposer = new EigenvalueDecomposition(matrix.transpose());
		Matrix eigenVectors = decomposer.getV();

		System.out.println("Getting first column....");
		int[] rows = new int[eigenVectors.getRowDimension()];
		for (int i = 0; i < rows.length; i++) {rows[i] = i;}

		Matrix eigenFirstColumn = eigenVectors.getMatrix(rows, 0, 0);

		System.out.println("Snatching first eigenvector...");
		double eigenSum = 0;
		for (int i = 0; i < eigenFirstColumn.getColumnDimension(); i++)
			eigenSum += eigenFirstColumn.get(i, 0);

		System.out.println("Normalising....");
		Matrix stationary = eigenFirstColumn;
		for (int i = 0; i < stationary.getColumnDimension(); i++)
			stationary.set(0, i, stationary.get(0, i)/eigenSum);

		System.out.println("Getting probability...");
		Matrix temp = stationary.transpose().times(eigenFirstColumn);
		probability = temp.get(0,0);

		System.out.println("Done\n");
		return probability;
	}
}
