package dop9;

public class Main {
    public static void main(String[] args) throws InterruptedException {

        MyRandomListSync list = new MyRandomListSync();
        MyThread.MyWriteThreadSync[] writeThreads = new MyThread.MyWriteThreadSync[5];
        MyThread.MyReadThreadSync[] readThreads = new MyThread.MyReadThreadSync[5];

        for (int i = 0; i < 5; i++) {
            writeThreads[i] = new MyThread.MyWriteThreadSync(list);
            readThreads[i] = new MyThread.MyReadThreadSync(list);
            writeThreads[i].start();
            readThreads[i].start();
        }

        Thread.sleep(3000);

        for (int i = 0; i < 5; i++) {
            writeThreads[i].setRunning(false);
            readThreads[i].setRunning(false);
        }

        for (int i = 0; i < 5; i++) {
            writeThreads[i].join();
            readThreads[i].join();
        }

        System.out.println("Number of non-zero elements: " + list.calcZero());
    }
}
