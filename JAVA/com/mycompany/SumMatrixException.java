package com.mycompany;

class SumMatrixException extends RuntimeException {
    public SumMatrixException(String message) {
        super("SumMatrixException: " + message);
    }

}