package lab2;

public class Main {
    public static void main(String[] args) {
        Matrix M1 = new Matrix(2);
        Matrix M2 = M1;

        M1.setElement(0,0,1);
        M1.setElement(0,1,1);
        M1.setElement(1,0,1);
        M1.setElement(1,1,0);

        System.out.println("Source matrix:");
        System.out.println(M1);

        for (int i = 1; i<=10; i++){
            M1 = M1.product(M2);
            System.out.println("Matrix in " + i + " power:");
            System.out.println(M1);
        }


    }
}