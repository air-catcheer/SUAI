package dop9;

import java.lang.Thread;
public class MyThread {
    public static class MyWriteThread extends Thread {

        private MyRandomList list;
        private boolean isRunning = true;
        public MyWriteThread(MyRandomList list) {
            this.list = list;
        }

        public void stopThread() {
            isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                int randomNumber = (int) (Math.random() * 100);
                if (randomNumber % 2 == 0) {
                    list.addNumber(randomNumber);
                } else {
                    list.removeNumber();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setRunning(boolean b) {
            isRunning = b;
        }
    }

    public static class MyReadThread extends Thread {
        private MyRandomList list;
        private boolean isRunning = true;

        public MyReadThread(MyRandomList list) {
            this.list = list;
        }
        public void stopThread() {
            isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                int count = list.calcZero();
                System.out.println("Number of non-zero elements: " + count);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setRunning(boolean b) {
            isRunning = b;
        }
    }

    //создать синхронизированную версию MyWriteThread и MyReadThread
    public static class MyWriteThreadSync extends Thread {
        private MyRandomListSync list;
        private boolean isRunning = true;

        public MyWriteThreadSync(MyRandomListSync list) {
            this.list = list;
        }

        public void stopThread() {
            isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                int randomNumber = (int) (Math.random() * 100);
                if (randomNumber % 2 == 0) {
                    list.addNumber(randomNumber);
                } else {
                    list.removeNumber();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setRunning(boolean b) {
            isRunning = b;
        }
    }
    //MyReadThread синхронизирован
    public static class MyReadThreadSync extends Thread {
        private MyRandomListSync list;
        private boolean isRunning = true;

        public MyReadThreadSync(MyRandomListSync list) {
            this.list = list;
        }

        public void stopThread() {
            isRunning = false;
        }

        @Override
        public void run() {
            while (isRunning) {
                int count = list.calcZero();
                System.out.println("Number of non-zero elements: " + count);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void setRunning(boolean b)
        {
            isRunning = b;
        }

    }
}