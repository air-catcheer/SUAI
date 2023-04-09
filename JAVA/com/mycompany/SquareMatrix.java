package com.mycompany;


class SquareMatrix extends Matrix {
    public SquareMatrix(int size) {
        super(size, size);
        for (int i = 0; i < size; i++) {
            this.matrix[i][i] = 1;
        }
    }

    public SquareMatrix() {
        this(1);
    }
    public SquareMatrix sum(SquareMatrix matrix) {

        if (this.rows != matrix.rows) {
            throw new SumMatrixException("Matrices have different sizes");
        }

        SquareMatrix result = new SquareMatrix(this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.matrix[i][j] = this.matrix[i][j] + matrix.matrix[i][j];
            }
        }

        return result;
    }

public SquareMatrix product(SquareMatrix matrix) {
        if (this.columns != matrix.rows) {
            throw new ProductMatrixException("Matrices have different sizes");
        }

        SquareMatrix result = new SquareMatrix(this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < matrix.columns; j++) {
                for (int k = 0; k < this.columns; k++) {
                    result.matrix[i][j] += this.matrix[i][k] * matrix.matrix[k][j];
                }
            }
        }

        return result;
    }

    public SquareMatrix product(int number) {
        SquareMatrix result = new SquareMatrix(this.rows);

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                result.matrix[i][j] = this.matrix[i][j] * number;
            }
        }

        return result;
    }
}
