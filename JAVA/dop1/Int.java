//дописать метод бинарного возведения в степень
package dop1;

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

    public void add(dop1.Int other) {
        this.value += other.value;
    }

    public void subtract(dop1.Int other) {
        this.value -= other.value;
    }

}
