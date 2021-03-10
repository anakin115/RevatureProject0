package util;

public abstract class MyCollection<T> {

    protected int maxSize;

    public abstract void add(T object);

    public abstract int size();

    public abstract void remove(T object);

    public abstract String toString();

    abstract boolean isEmpty();

    protected abstract void clear();

    public abstract int indexOf(T object);

    public abstract Node<T> atIndex(int i);
}
