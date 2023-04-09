package dop9;
import java.util.ArrayList;
import java.util.List;

public class MyRandomList {
    private List<Integer> list = new ArrayList<>();

    public void addNumber(int number) {
        list.add(number);
    }

    public void removeNumber() {
        if (!list.isEmpty()) {
            int index = (int) (Math.random() * list.size());
            list.remove(index);
        }
    }

    public int calcZero() {
        int count = 0;
        for (int i : list) {
            if (i != 0) {
                count++;
            }
        }
        return count;
    }
}
