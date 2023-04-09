package dop8;

public class MergeSort {
    //сделать многопоточную реализацию сортировки слиянием
    //сравнить скорость работы с однопоточной реализацией

    public static void mergeSort(int[] array, int threadNum) {
        if (threadNum == 1) {
            mergeSort(array);
        } else {
            mergeSortParallel(array, threadNum);
        }
    }

    private static void mergeSortParallel(int[] array, int threadNum) {
        int[] array1 = new int[array.length / 2];
        int[] array2 = new int[array.length - array1.length];
        System.arraycopy(array, 0, array1, 0, array1.length);
        System.arraycopy(array, array1.length, array2, 0, array2.length);
        Thread thread1 = new Thread(() -> mergeSort(array1, threadNum / 2));
        Thread thread2 = new Thread(() -> mergeSort(array2, threadNum / 2));
        thread1.start();
        thread2.start();
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        merge(array1, array2, array);
    }

    private static void merge(int[] array1, int[] array2, int[] array) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < array1.length && j < array2.length) {
            if (array1[i] < array2[j]) {
                array[k] = array1[i];
                i++;
            } else {
                array[k] = array2[j];
                j++;
            }
            k++;
        }
        while (i < array1.length) {
            array[k] = array1[i];
            i++;
            k++;
        }
        while (j < array2.length) {
            array[k] = array2[j];
            j++;
            k++;
        }
    }

    static void mergeSort(int[] array) {
        if (array.length > 1) {
            int[] array1 = new int[array.length / 2];
            int[] array2 = new int[array.length - array1.length];
            System.arraycopy(array, 0, array1, 0, array1.length);
            System.arraycopy(array, array1.length, array2, 0, array2.length);
            mergeSort(array1);
            mergeSort(array2);
            merge(array1, array2, array);
        }
    }
}
