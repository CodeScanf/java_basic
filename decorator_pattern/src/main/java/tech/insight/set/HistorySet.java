package tech.insight.set;

import java.util.*;

/**
 * @author lijiaobin
 * @version 1.0
 * @project java_basic
 * @description
 * @date 2025/5/10 21:38:50
 */
public class HistorySet<E> implements Set<E> {
    public HistorySet(Set<E> set) {
        this.delegate = set;
    }

    List<E> removeList = new ArrayList<>();

    private final Set<E> delegate;

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return delegate.contains(o);
    }

    @Override
    public Iterator<E> iterator() {
        return delegate.iterator();
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return delegate.toArray(a);
    }

    @Override
    public boolean add(E e) {
        return delegate.add(e);
    }

    @Override
    public boolean remove(Object o) {
        if (delegate.remove(o)){
            removeList.add((E) o);
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return delegate.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return delegate.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return delegate.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return delegate.removeAll(c);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public String toString() {
        return delegate.toString() + "remove list : " + removeList;
    }
}
