package lab3;

public class MatrixException extends RuntimeException {
    public MatrixException(String message) {
        super("MatrixException: " + message);
    }
}
