//создать класс Vector, добавить в класс Matrix метод умножения на вектор.
package dop2;

public class Main {
    public static void main(String[] args) {
        double[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        double[] vector = {1, 2, 3};
        Matrix matrix1 = new Matrix(matrix);
        Vector vector1 = new Vector(vector);
        double[] result = matrix1.multiply(vector1);
        for (int i = 0; i < result.length; i++) {
            System.out.println(result[i]);
        }
    }
}



