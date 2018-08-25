package com.company;


import org.apache.commons.math3.Field;
import org.apache.commons.math3.complex.Complex;
import org.apache.commons.math3.complex.ComplexField;
import org.apache.commons.math3.linear.*;

public class Calculator
{
	private double probability = 0.5;   //Initial values,
	private double constantC = 0.30210; //currently just for testing.
	private Complex[][] matrix;
	private FieldMatrix<Complex> complexMatrix;
	private RealMatrix realMatrix;
	private Double tempDouble;
	private int size;


	public double CalculateC(double target)
	{
//		System.out.println("Adjusting C value...");
		while (Math.abs(target - probability) > 10e-8)
		{
			// -------------------- C value things -----------------------------
			if (constantC < 0.1)
				constantC = constantC + (target - probability) / 8;
			else
				constantC = constantC + (target - probability) / 2;




			// -------------------- Matrix things ------------------------------
//			System.out.println("Creating matrix...");
			tempDouble = Math.ceil(1 / constantC);
			size = tempDouble.intValue();
			System.out.println("Size: " + size + ". Constant C = " + constantC);
			matrix = new Complex[size][size];
			for (int i = 0; i < size; i++)
				for (int j = 0; j < size; j++)
					matrix[i][j] = new Complex(0,0);
			complexMatrix = MatrixUtils.createFieldMatrix(matrix);
			realMatrix = MatrixUtils.createRealMatrix(size, size);


//			System.out.println("Populating...");
			for (int n = 1; n < complexMatrix.getRowDimension()+1; n++)
			if (n*constantC < 1)
				complexMatrix.setEntry(n-1, 0, new Complex(n*constantC, 0));
			else
				complexMatrix.setEntry(n-1, 0, new Complex(1, 0));

			for (int n = 0; n < complexMatrix.getRowDimension() - 1; n++)
				complexMatrix.setEntry(n,n+1, new Complex(1-complexMatrix.getEntry(n, 0).getReal(), 0));

			for (int n = 1; n < realMatrix.getRowDimension()+1; n++)
			if (n*constantC < 1)
				realMatrix.setEntry(n-1, 0,n*constantC);
			else
				realMatrix.setEntry(n-1, 0,1);

			for (int n = 0; n < realMatrix.getRowDimension() - 1; n++)
				realMatrix.setEntry(n,n+1, 1-realMatrix.getEntry(n, 0));

			System.out.println("Matrix done");



			// ------------------ Eigenvector things -------------------------
			EigenDecomposition realDecomposition = new EigenDecomposition(realMatrix.transpose());
			RealMatrix realEigens = realDecomposition.getV();
			RealMatrix imagEigens = realDecomposition.getD();

			System.out.println("This is the last print!");
		}
		return constantC;
	}
}
