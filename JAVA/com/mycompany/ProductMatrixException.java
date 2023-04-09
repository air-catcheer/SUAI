package com.mycompany;

public class ProductMatrixException extends RuntimeException{
    public ProductMatrixException(String message) {
        super("ProductMatrixException: " + message);
    }
}
