package dop5;

public class ArrayList implements IStack{
    //реализовать интерфейс IStack с помощью массива
    private Object[] array;
    private int size;

    public ArrayList() {
        array = new Object[10];
        size = 0;
    }

    public void push(Object element) {
        if (size == array.length) {
            Object[] newArray = new Object[array.length * 2];
            System.arraycopy(array, 0, newArray, 0, array.length);
            array = newArray;
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
