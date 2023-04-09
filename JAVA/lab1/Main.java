package lab1;
public class Main {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        Int a = new Int();
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
