package dop1;

import dop1.Int;

public class Main {
    public static void main(String[] args) {
        int result = powerFunction(2, 5);
        System.out.println(result);
    }

    static int powerFunction(int base, int exponent) {
        int result = 1;
        for (int i = 0; i < exponent; i++) {
            result = base * result;
        }
        return result;
    }

    public static void test() {
        dop1.Int a = new Int();
        a.increment();

        for (int i = 0; i < 7; i++) {
            a.add(a);
            System.out.println(a);
        }

        for (int i = 0; i < 3; i++) {
            a.decrement();
        }

        System.out.println(a);

        for (int i = 0; i < 3; i++) {
            a.add(a);
            System.out.println(a);
        }
    }
}
