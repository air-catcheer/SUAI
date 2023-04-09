package lab4;
import java.util.LinkedList;
import java.util.ListIterator;

class SortedIntegerList {
    private LinkedList<Integer> list;
    boolean allowDuplicates;

    public SortedIntegerList(boolean allowDuplicates) {
        this.allowDuplicates = allowDuplicates;
        list = new LinkedList<Integer>();
    }

    public SortedIntegerList() {
        this(true);
    }

    public void add(int value) {
        if (list.isEmpty()) {
            list.add(value);
        } else {
            ListIterator<Integer> iterator = list.listIterator();
            while (iterator.hasNext()) {
                Integer current = iterator.next();

                if (current > value) {
                    iterator.previous();
                    iterator.add(value);
                    return;
                }

                if (current == value && !allowDuplicates) {
                    return;
                }
            }
            iterator.add(value);
        }
    }

    public void remove(int value) {
        list.removeFirstOccurrence(value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof SortedIntegerList)) {
            return false;
        }

        SortedIntegerList other = (SortedIntegerList) obj;

        if (this.list.size() != other.list.size()) {
            return false;
        }

        ListIterator<Integer> iterator1 = this.list.listIterator();
        ListIterator<Integer> iterator2 = other.list.listIterator();

        while (iterator1.hasNext()) {
            if (iterator1.next() != iterator2.next()) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        for (Integer i : list) {
            sb.append(i);
            sb.append(", ");
        }

        if (sb.length() > 1) {
            sb.delete(sb.length() - 2, sb.length());
        }

        sb.append("]");
        return sb.toString();
    }

    public class SortedList {
        public SortedList(boolean b) {
        }

        //метод пересечения двух списков
        public static LinkedList<Integer> intersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
            LinkedList<Integer> result = new LinkedList<Integer>();
            ListIterator<Integer> iterator1 = list1.listIterator();
            ListIterator<Integer> iterator2 = list2.listIterator();
            while (iterator1.hasNext() && iterator2.hasNext()) {
                Integer current1 = iterator1.next();
                Integer current2 = iterator2.next();
                if (current1 == current2) {
                    result.add(current1);
                }
            }
            return result;
        }
        //вывод пересечения двух списков
        public static void printIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
            System.out.println("Intersection: " + intersection(list1, list2));
        }

        public static void main(String[] args) {
            LinkedList<Integer> list1 = new LinkedList<Integer>();
            LinkedList<Integer> list2 = new LinkedList<Integer>();
            for (int i = 0; i < 10; i++) {
                list1.add(i);
                list2.add(i);
            }
            printIntersection(list1, list2);
        }
    }
    public static void main(String[] args) {
        SortedIntegerList list = new SortedIntegerList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.add(6);
        list.add(7);
        list.add(8);
        list.add(9);
        list.add(10);
        System.out.println(list);
    }

}