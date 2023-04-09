package dop9;

import java.util.ArrayList;
import java.util.List;

public class MyRandomListSync {
    private List<Integer> list = new ArrayList<>();

    public synchronized void addNumber(int number) {
        list.add(number);
    }

    public synchronized void removeNumber() {
        if (!list.isEmpty()) {
            int index = (int) (Math.random() * list.size());
            list.remove(index);
        }
    }

    public synchronized int calcZero() {
        int count = 0;
        for (int i : list) {
            if (i != 0) {
                count++;
            }
        }
        return count;
    }

    private boolean isSynchronized = false;
    public void setSynchronized(boolean isSynchronized) {
        this.isSynchronized = isSynchronized;
    }
    public void add(int number) {
        if (isSynchronized) {
            synchronized (this) {
                list.add(number);
            }
        } else {
            list.add(number);
        }
    }

    public int get(int index) {
        if (isSynchronized) {
            synchronized (this) {
                return list.get(index);
            }
        } else {
            return list.get(index);
        }
    }
}
