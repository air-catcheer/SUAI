package dop9;

public class Main2 {
    MyRandomList list = new MyRandomList();
    MyThread.MyWriteThread[] writeThreads = new MyThread.MyWriteThread[5];
    MyThread.MyReadThread[] readThreads = new MyThread.MyReadThread[5];

    public void start() {
        for (int i = 0; i < 5; i++) {
            writeThreads[i] = new MyThread.MyWriteThread(list);
            readThreads[i] = new MyThread.MyReadThread(list);
            writeThreads[i].start();
            readThreads[i].start();
        }
    }

    public void wait3sec() throws InterruptedException {
        Thread.sleep(3000);
    }

    public void stop() {
        for (int i = 0; i < 5; i++) {
            writeThreads[i].setRunning(false);
            readThreads[i].setRunning(false);
        }
    }

    public void join() throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            writeThreads[i].join();
            readThreads[i].join();
        }
    }

    public void printResult() {
        System.out.println("Number of non-zero elements: " + list.calcZero());
    }

    public static void main(String[] args) throws InterruptedException {
        Main2 main = new Main2();
        main.start();
        main.wait3sec();
        main.stop();
        main.join();
        main.printResult();
    }
}
