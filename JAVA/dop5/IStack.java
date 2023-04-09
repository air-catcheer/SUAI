package dop5;

public interface IStack {

    public void push(Object element);

    public Object pop();

    public int size();

    public boolean equals(IStack stack);

    public String toString();

}
