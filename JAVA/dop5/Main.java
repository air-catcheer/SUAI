package dop5;

public class Main {
    //реальзовать функцию reserve, которая принимает в качестве
    //параметра ISack и int value и меняет порядок цифр в числе value
    //на обратный и помещает его в стек
    public static void reserve(IStack stack, int value) {
        while (value != 0) {
            stack.push(value % 10);
            value /= 10;
        }
    }

    //проверка reserve на основе LinkedList
    public static void test1() {

        IStack stack = (IStack) new LinkedList();
        reserve(stack, 123456789);
        System.out.println(stack);
    }

    //проверка reserve на основе ArrayList
    public static void test2() {
        IStack stack = (IStack) new ArrayList();
        reserve(stack, 123);
        System.out.println(stack);
    }

    //проверить работу функции reserve на основе FixedLengthArray
    public static void test3() {
        IStack stack = (IStack) new FixedLengthArray(10);
        reserve(stack, 56789);
        System.out.println(stack);
    }

    //реальзовать try to reserve и вывести исключение через catch
    public static void test4() {
        IStack stack = (IStack) new FixedLengthArray(3);
        try {
            reserve(stack, 123456789);
        } catch (RuntimeException e) {
            System.out.println("RuntimeException: Stack is full");
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }
}

