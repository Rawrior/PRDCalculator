package com.company;

import Jama.Matrix;
import Jama.EigenvalueDecomposition;

public class WildCalculator
{
	private double probability = 0.5;
	private double ConstantC = 0.30210;
	private Matrix matrix;
	private Double tempDouble;
	private int size;

	public double CalculateC(double target)
	{
		ConstantC = adjustC(ConstantC, target, probability);

		return ConstantC;
	}

	private double adjustC(double constantC, double target, double probability)
	{
//		System.out.println("Adjusting C value...");
		while (Math.abs(target - probability) > 10e-8)
		{
			if (constantC < 0.1)
				constantC = constantC + (target - probability)/8;
			else
				constantC = constantC + (target - probability)/2;

//			System.out.println("Creating matrix...");

			tempDouble = Math.ceil(1 / constantC);
			size = tempDouble.intValue();
			System.out.println("Size: " + size + ". Constant C = " + constantC);
			matrix = new Matrix(size, size);

//			System.out.println("Populating...");
			for (int n = 0; n < matrix.getRowDimension(); n++)
				if (n*constantC < 1)
					matrix.set(n, 1, n*constantC);
				else
					matrix.set(n, 1, 1);

			for (int n = 0; n < matrix.getRowDimension() - 1; n++)
				matrix.set(n,n+1, 1-matrix.get(n, 1));

			System.out.println("Matrix done");

//			System.out.println("Setting up eigenmatrix...");
			EigenvalueDecomposition decomposer = new EigenvalueDecomposition(matrix.transpose());
			Matrix eigenVectors = decomposer.getV();

//			System.out.println("Getting first column....");
			int[] rows = new int[eigenVectors.getRowDimension()];
			for (int i = 0; i < rows.length; i++) {rows[i] = i;}

			Matrix eigenFirstColumn = eigenVectors.getMatrix(rows, 1, 1);

//			System.out.println("Snatching first eigenvector...");
			double eigenSum = 0;
			for (int i = 0; i < eigenFirstColumn.getColumnDimension(); i++)
				eigenSum += eigenFirstColumn.get(i, 0);

//			System.out.println("Normalising....");
			Matrix stationary = eigenFirstColumn;
			for (int i = 0; i < stationary.getColumnDimension(); i++)
				stationary.set(0, i, stationary.get(0, i)/eigenSum);

//			System.out.println("Getting probability...");
			Matrix temp = stationary.transpose().times(eigenFirstColumn);
			probability = temp.get(0,0);

			System.out.println("Eigenvalue done.");
			System.out.println("Current C value = " + constantC);
		}

		System.out.println("Done\n");
		return constantC;
	}
}
