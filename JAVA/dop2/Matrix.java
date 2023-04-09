package dop2;

import java.util.Arrays;

public class Matrix {
     private double[][] matrix;

     public Matrix(double[][] matrix) {
         this.matrix = matrix;
     }

     public double[][] getMatrix() {
         return matrix;
     }

     public void setMatrix(double[][] matrix) {
         this.matrix = matrix;
     }

        public double[] multiply(Vector vector) {
            double[] result = new double[vector.getVector().length];
            for (int i = 0; i < vector.getVector().length; i++) {
                for (int j = 0; j < vector.getVector().length; j++) {
                    result[i] += matrix[i][j] * vector.getVector()[j];
                }
            }
            return result;
        }
    }