import java.util.*;

/**
 * Типізована колекція типу List з внутрішньою структурою однозв’язного списку.
 * Елементи — об’єкти класу MusicTrack (з ЛР №5).
 *
 * @param <T> тип елементів (має бути підтипом MusicTrack)
 * @author Марія Зозуля
 * @group ІС-34
 */
public class LinkedMusicList<T extends MusicTrack> implements List<T> {

    /** Вузол однозв’язного списку */
    private static class Node<E> {
        E data;
        Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }

    private Node<T> head;
    private int size;

    /** Конструктор 1: порожня колекція */
    public LinkedMusicList() {
        head = null;
        size = 0;
    }

    /** Конструктор 2: колекція з одного об’єкта */
    public LinkedMusicList(T element) {
        this();
        if (element == null) throw new NullPointerException("Елемент не може бути null");
        add(element);
    }

    /** Конструктор 3: колекція зі стандартної колекції */
    public LinkedMusicList(Collection<? extends T> collection) {
        this();
        if (collection == null) throw new NullPointerException("Колекція не може бути null");
        addAll(collection);
    }

    // --- List методи ---

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (Node<T> current = head; current != null; current = current.next) {
            if (o.equals(current.data)) return true;
        }
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            array[i++] = current.data;
        }
        return array;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <U> U[] toArray(U[] a) {
        if (a.length < size) {
            a = (U[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
        }
        int i = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            a[i++] = (U) current.data;
        }
        if (a.length > size) a[size] = null;
        return a;
    }

    @Override
    public boolean add(T element) {
        if (element == null) throw new NullPointerException("Елемент не може бути null");
        Node<T> newNode = new Node<>(element);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (o == null || head == null) return false;
        if (o.equals(head.data)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null && !o.equals(current.next.data)) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        for (Object o : c) {
            if (!contains(o)) return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) throw new NullPointerException();
        boolean modified = false;
        for (T element : c) {
            if (add(element)) modified = true;
        }
        return modified;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException("Індекс: " + index);
        if (c == null) throw new NullPointerException();
        if (c.isEmpty()) return false;

        Object[] elements = c.toArray();
        for (int i = 0; i < elements.length; i++) {
            add(index + i, (T) elements[i]);
        }
        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean modified = false;
        for (Object o : c) {
            while (remove(o)) modified = true;
        }
        return modified;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) throw new NullPointerException();
        boolean modified = false;
        Node<T> current = head;
        Node<T> prev = null;
        while (current != null) {
            if (!c.contains(current.data)) {
                if (prev == null) {
                    head = current.next;
                } else {
                    prev.next = current.next;
                }
                size--;
                modified = true;
                current = (prev == null) ? head : prev.next;
            } else {
                prev = current;
                current = current.next;
            }
        }
        return modified;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }

    @Override
    public T get(int index) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);
        if (element == null) throw new NullPointerException();
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        T old = current.data;
        current.data = element;
        return old;
    }

    @Override
    public void add(int index, T element) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
        if (element == null) throw new NullPointerException();
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.next = head;
            head = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);
        T removed;
        if (index == 0) {
            removed = head.data;
            head = head.next;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            removed = current.next.data;
            current.next = current.next.next;
        }
        size--;
        return removed;
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) return -1;
        int index = 0;
        for (Node<T> current = head; current != null; current = current.next) {
            if (o.equals(current.data)) return index;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) return -1;
        int index = 0;
        int last = -1;
        for (Node<T> current = head; current != null; current = current.next) {
            if (o.equals(current.data)) last = index;
            index++;
        }
        return last;
    }

    @Override
    public ListIterator<T> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        checkIndexInclusive(index);
        return new ListIterator<>() {
            private Node<T> current = getNode(index);
            private Node<T> lastReturned = null;
            private int currentIndex = index;

            private Node<T> getNode(int idx) {
                Node<T> node = head;
                for (int i = 0; i < idx; i++) {
                    node = node.next;
                }
                return node;
            }

            @Override public boolean hasNext() { return current != null; }
            @Override public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                lastReturned = current;
                T data = current.data;
                current = current.next;
                currentIndex++;
                return data;
            }
            @Override public boolean hasPrevious() { return currentIndex > 0; }
            @Override public T previous() {
                if (!hasPrevious()) throw new NoSuchElementException();
                currentIndex--;
                current = (current == head) ? head : getNode(currentIndex);
                lastReturned = current;
                return current.data;
            }
            @Override public int nextIndex() { return currentIndex; }
            @Override public int previousIndex() { return currentIndex - 1; }
            @Override public void remove() {
                if (lastReturned == null) throw new IllegalStateException();
                LinkedMusicList.this.remove(currentIndex - 1);
                lastReturned = null;
                currentIndex--;
            }
            @Override public void set(T e) {
                if (lastReturned == null) throw new IllegalStateException();
                lastReturned.data = e;
            }
            @Override public void add(T e) {
                LinkedMusicList.this.add(currentIndex++, e);
                lastReturned = null;
            }
        };
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        checkRange(fromIndex, toIndex);
        LinkedMusicList<T> sub = new LinkedMusicList<>();
        Node<T> current = getNode(fromIndex);
        for (int i = fromIndex; i < toIndex; i++) {
            sub.add(current.data);
            current = current.next;
        }
        return sub;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException("Індекс: " + index + ", Розмір: " + size);
    }

    private void checkIndexInclusive(int index) {
        if (index < 0 || index > size) throw new IndexOutOfBoundsException();
    }

    private void checkRange(int from, int to) {
        if (from < 0 || to > size || from > to) throw new IndexOutOfBoundsException();
    }

    private Node<T> getNode(int index) {
        checkIndex(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder("[");
        for (Node<T> current = head; current != null; current = current.next) {
            sb.append(current.data);
            if (current.next != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}