package dop8;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[1000000];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * 1000000);
        }
        int[] array1 = array.clone();
        int[] array2 = array.clone();
        int[] array3 = array.clone();
        int[] array4 = array.clone();
        int[] array5 = array.clone();
        int[] array6 = array.clone();
        long start = System.currentTimeMillis();
        MergeSort.mergeSort(array1, 1);
        long end = System.currentTimeMillis();

        System.out.println("Merge sort for 1000000 elements: ");
        System.out.println("1 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array2, 2);
        end = System.currentTimeMillis();
        System.out.println("2 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array3, 4);
        end = System.currentTimeMillis();
        System.out.println("4 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array4, 8);
        end = System.currentTimeMillis();
        System.out.println("8 thread: " + (end - start)  + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array5, 16);
        end = System.currentTimeMillis();
        System.out.println("16 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array6, 32);
        end = System.currentTimeMillis();
        System.out.println("32 thread: " + (end - start) + " ms");


        int[] array7 = new int[1000000];
        for (int i = 0; i < array7.length; i++) {
            array7[i] = (int) (Math.random() * 1000000);
        }
        int[] array8 = array7.clone();
        int[] array9 = array7.clone();
        int[] array10 = array7.clone();
        int[] array11 = array7.clone();
        int[] array12 = array7.clone();
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array7, 1);
        end = System.currentTimeMillis();
        System.out.println("Parallel merge sort for 1000000 elements:");
        System.out.println("1 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array8, 2);
        end = System.currentTimeMillis();
        System.out.println("2 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array9, 4);
        end = System.currentTimeMillis();
        System.out.println("4 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array10, 8);
        end = System.currentTimeMillis();
        System.out.println("8 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array11, 16);
        end = System.currentTimeMillis();
        System.out.println("16 thread: " + (end - start) + " ms");
        start = System.currentTimeMillis();
        MergeSort.mergeSort(array12, 32);
        end = System.currentTimeMillis();
        System.out.println("32 thread: " + (end - start) + " ms");
    }
}
