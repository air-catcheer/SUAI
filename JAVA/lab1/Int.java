package lab1;
public class Int {
    private int value;

    public Int() {
        this.value = 0;
    }

    public String toString() {
        return String.valueOf(this.value);
    }

    public void increment() {
        this.value++;
    }

    public void decrement() {
        this.value--;
    }

    public void add(Int other) {
        this.value += other.value;
    }

    public void subtract(Int other) {
        this.value -= other.value;
    }
}
