package lab9;

public class Deadlock {

    public static void main(String[] args) {
        Object lock1 = new Object();
        Object lock2 = new Object();
        Object lock3 = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock1) {
                System.out.println("Thread 1 locked lock1");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock2) {
                    System.out.println("Thread 1 locked lock2");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock3) {
                        System.out.println("Thread 1 locked lock3");
                    }
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            synchronized (lock2) {
                System.out.println("Thread 2 locked lock2");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock3) {
                    System.out.println("Thread 2 locked lock3");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock1) {
                        System.out.println("Thread 2 locked lock1");
                    }
                }
            }
        });

        Thread thread3 = new Thread(() -> {
            synchronized (lock3) {
                System.out.println("Thread 3 locked lock3");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock1) {
                    System.out.println("Thread 3 locked lock1");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lock2) {
                        System.out.println("Thread 3 locked lock2");
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();
    }
}
