package dop4;

import java.util.LinkedList;
import java.util.ListIterator;

//исключение, которое выбрасывается, если типы списков не совпадают
public class DifferentTypesException extends Exception {
    public DifferentTypesException(String message) {
        super("DifferentTypesException: " + message);
    }
}