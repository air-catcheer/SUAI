package dop4;

import java.util.LinkedList;
import java.util.ListIterator;

public class Main {
    public static void main(String[] args) throws DifferentTypesException {
        SortedList originalList = new SortedList();

        // проверка пересечения через SortedList
        SortedList list1 = new SortedList();
        SortedList list2 = new SortedList();
        SortedList list3 = new SortedList();
        SortedList list4 = new SortedList();


        //добавить в 4 список элементы
        list4.add("a");
        list4.add("b");
        list4.add("c");
        list4.add("1");
        list4.add("2");

        for (int i = 0; i < 10; i++) {
            list1.add(i);
            list2.add(i);
            list3.add(i);
        }

        list1.add(10);
        list2.add(11);
        list3.add(12);

        System.out.println("Input data:");

        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        System.out.println("list4: " + list4);

        System.out.println("Check types of elements:");

        System.out.println("list1.compareTypes(list2): " + list1.compareTypes(list2));
        System.out.println("list1.compareTypes(list3): " + list1.compareTypes(list3));
        System.out.println("list1.compareTypes(list4): " + list1.compareTypes(list4));

        System.out.println("Intersect:");

        System.out.println("list1.intersect(list2): " + list1.intersect(list2));
        System.out.println("list1.intersect(list3): " + list1.intersect(list3));

        //исключение
        try {
            System.out.println("list1.intersect(list4): " + list1.intersect(list4));
        } catch (DifferentTypesException e) {
            System.out.println(e.getMessage());
        }



    }
}
