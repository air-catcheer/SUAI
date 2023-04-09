package dop5;

public class LinkedList implements IStack {
    //реализовать интерфейс IStack при помощи связного списка
    private Node head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public void push(Object element) {
        Node node = new Node(element);
        node.next = head;
        head = node;
        size++;
    }

    public Object pop() {
        if (size == 0) {
            return null;
        }
        Object element = head.element;
        head = head.next;
        return element;
    }

    public int size() {
        return size;
    }

    public boolean equals(IStack stack) {
        if (stack.size() != size) {
            return false;
        }
        Node node = head;
        for (int i = 0; i < size; i++) {
            if (!node.element.equals(stack.pop())) {
                return false;
            }
            node = node.next;
        }
        return true;
    }
    //перекрыть метод toString() так, чтобы он возвращал строку задом наперед
    public String toString() {
        String result = "";
        Node node = head;
        for (int i = 0; i < size; i++) {
            result = node.element + " " + result;
            node = node.next;
        }
        return result;
    }
}
