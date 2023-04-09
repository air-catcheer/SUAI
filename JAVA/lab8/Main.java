package lab8;

import lab5.UsualMatrix;

public class Main {
    public static void main(String[] args) {
        UsualMatrix[] matrices = new UsualMatrix[4];
        for (int i = 0; i < matrices.length; i++) {
            matrices[i] = new UsualMatrix(1000);
        }

        matrices[0].randomize();
        matrices[1].randomize();

        MeasureTime.measureTime("Standard multiplication", () -> {
            matrices[2] = matrices[0].product(matrices[1]);
        }, 10);


        ParallelMatrixProduct pmp = new ParallelMatrixProduct();

        MeasureTime.measureTime("Parallel multiplication", () -> {
            matrices[3] = pmp.product(matrices[0], matrices[1]);
        }, 10);

        System.out.println("Matrices are equal: " + matrices[2].equals(matrices[3]));
    }
}