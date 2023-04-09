package dop5;

public class FixedLengthArray implements IStack{
    //реализовать интерфейс IStack с помощью массива фиксированной длины
    private Object[] array;
    private int size;

    public FixedLengthArray(int length) {
        array = new Object[length];
        size = 0;
    }

    public void push(Object element) throws RuntimeException {
        if (size == array.length) {
            throw new RuntimeException();
        }
        array[size] = element;
        size++;
    }

    public Object pop() {
        if (size == 0) {
            return null;
        }
        size--;
        Object element = array[size];
        array[size] = null;
        return element;
    }

    public int size() {
        return size;
    }

    public boolean equals(IStack stack) {
        if (stack.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!array[i].equals(stack.pop())) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += array[i] + " ";
        }
        return result;
    }
}
