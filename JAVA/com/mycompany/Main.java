package com.mycompany;

public class Main {
    public static void main(String[] args) {

        SquareMatrix matrix1 = new SquareMatrix(2);
        SquareMatrix matrix2 = new SquareMatrix(2);

        matrix1.setElement(0, 0, 1);
        matrix1.setElement(0, 1, 1);
        matrix1.setElement(1, 0, 2);
        matrix1.setElement(1, 1, 2);

        matrix2.setElement(0, 0, 3);
        matrix2.setElement(0, 1, 3);
        matrix2.setElement(1, 0, 4);
        matrix2.setElement(1, 1, 4);


        System.out.println("matrix 1:");
        System.out.println(matrix1);
        System.out.println("matrix 2:");
        System.out.println(matrix2);

        SquareMatrix matrix5 = matrix1.sum(matrix2);

        System.out.println("matrix 1 + matrix 2:");
        System.out.println(matrix5);

        SquareMatrix matrix6 = matrix1.product(matrix2);

        System.out.println("matrix 1 * matrix 2:");
        System.out.println(matrix6);

        System.out.println();
        System.out.println("test of exceptions:");

        SquareMatrix matrix3 = new SquareMatrix(3);

        SquareMatrix matrix4 = new SquareMatrix(2);

        try {
            SquareMatrix matrix7 = matrix3.sum(matrix4);
            System.out.println(matrix7);
        } catch (SumMatrixException e) {
            System.out.println(e.getMessage());
        }

        try {
            SquareMatrix matrix7 = matrix3.product(matrix4);
            System.out.println(matrix7);
        } catch (ProductMatrixException e) {
            System.out.println(e.getMessage());
        }
    }

}
