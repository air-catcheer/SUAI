package dop4;
import java.util.LinkedList;
import java.util.ListIterator;

public class SortedList<T extends Comparable<T>> extends LinkedList<T> {
    public SortedList() {
        super();
    }

    public SortedList(SortedList<T> list) {
        super(list);
    }
    //сравнение типов элементов в списке
public boolean compareTypes(SortedList<T> list) {
        return this.getFirst().getClass().equals(list.getFirst().getClass());
    }

    //если типы совпадают, то добавляем элемент в список
    @Override
    public boolean add(T element) {
        ListIterator<T> iterator = this.listIterator();
        while (iterator.hasNext()) {
            if (iterator.next().compareTo(element) > 0) {
                iterator.previous();
                iterator.add(element);
                return true;
            }
        }
        iterator.add(element);
        return true;
    }

    //если типы не совпадают, то выбрасываем исключение
    public SortedList<T> intersect(SortedList<T> list) throws DifferentTypesException {
        if (!compareTypes(list)) {
            throw new DifferentTypesException("Different types of elements in lists");
        }
        SortedList<T> result = new SortedList<>();
        ListIterator<T> iterator = this.listIterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (list.contains(element)) {
                result.add(element);
            }
        }
        return result;
    }

    //переопределение метода toString
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        ListIterator<T> iterator = this.listIterator();
        while (iterator.hasNext()) {
            result.append(iterator.next()).append(" ");
        }
        return result.toString();
    }

    //
}