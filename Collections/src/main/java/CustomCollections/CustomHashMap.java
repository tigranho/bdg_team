package CustomCollections;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @param <K>
 * @param <V>
 * @author VaheAvetikyan
 */
public class CustomHashMap<K, V> implements Map<K, V> {

    /**
     * Helper Node class
     *
     * @param <T>
     * @param <U>
     */
    private class Node<T, U> {
        private final T key;
        private final U value;
        private Node<T, U> next;

        public Node(T key, U value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public T getKey() {
            return key;
        }

        public U getValue() {
            return value;
        }

        public Node<T, U> getNext() {
            return next;
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null) {
                return false;
            }
            if (o instanceof Node) {
                Node<T, U> tempObject = (Node) o;
                return this.key == tempObject.key;
            }
            return false;
        }

        @Override
        public final String toString() {
            return key + " =  value;
        }
    }

    private final int DEFAULT_CAPACITY = 100;
    private int size = 0;
    private Node<K, V>[] table = new Node[DEFAULT_CAPACITY];

    public CustomHashMap() {
    }

    public CustomHashMap(Map<? extends K, ? extends V> m) {
        putAll(m);
    }

    /**
     * The number of the elements in HashMap.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * Returns true if HashMap is empty.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * @return true if @param key is found in HashMap
     */
    @Override
    public boolean containsKey(Object key) {
        int hashCode = Math.abs(key.hashCode() % this.table.length);
        if (table[hashCode] == null) {
            return false;
        }
        K tempKey = (K) key;
        if (tempKey.equals(table[hashCode].getKey())) {
            return true;
        } else {
            Node<K, V> next = table[hashCode];
            while (next.getNext() != null) {
                next = next.getNext();
                if (tempKey.equals(next.getKey())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        V v = (V) value;
        for (Node<K, V> n : table) {
            if (n != null) {
                if (n.getValue().equals(v)) {
                    return true;
                } else {
                    while (n.getNext() != null) {
                        n = n.getNext();
                        if (n.getValue().equals(v)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    /**
     * @return value based on @param key
     */
    @Override
    public V get(Object key) {
        int hashCode = Math.abs(key.hashCode() % this.table.length);
        if (table[hashCode] == null) {
            return null;
        }
        K tempKey = (K) key;
        if (tempKey.equals(table[hashCode].getKey())) {
            return (V) table[hashCode].getValue();
        } else {
            Node<K, V> next = table[hashCode];
            while (next.getNext() != null) {
                next = next.getNext();
                if (tempKey.equals(next.getKey())) {
                    return next.getValue();
                }
            }
            return next.getValue();
        }
    }

    /**
     * Inserts new element to HashMap
     *
     * @param key
     * @param value
     * @return inserted element value
     */
    @Override
    public V put(K key, V value) {
        int hashCode = Math.abs(key.hashCode() % this.table.length);
        if (table[hashCode] != null) {
            K tempKey = (K) key;
            if (tempKey.equals(table[hashCode].getKey())) {
                table[hashCode] = new Node<>(key, value);
            } else {
                Node<K, V> next = table[hashCode];
                while (next.getNext() != null) {
                    next = next.getNext();
                }
                this.size++;
                next.next = new Node<>(key, value);
            }
        } else {
            this.size++;
            table[hashCode] = new Node<>(key, value);
        }
        return value;
    }

    /**
     * Removes element from HashMap
     *
     * @param key is the key of the object to be removed
     * @return removed objects value
     */
    @Override
    public V remove(Object key) {
        int hashCode = Math.abs(key.hashCode() % this.table.length);
        K tempKey = (K) key;
        if (table[hashCode].getNext() == null && tempKey.equals(table[hashCode].getKey())) {
            Node<K, V> temp = table[hashCode];
            table[hashCode] = null;
            this.size--;
            return temp.getValue();
        } else {
            Node<K, V> next = table[hashCode];
            while (next.getNext() != null) {
                if (tempKey.equals(next.getKey())) {
                    this.size--;
                    return next.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        m.forEach(this::put);
    }

    /**
     * Clears all elements
     */
    @Override
    public void clear() {
        // Dereferencing table array
        this.table = new Node[DEFAULT_CAPACITY];
        this.size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> setOfKeys = new HashSet<>();
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            if (table[i] != null) {
                Node<K, V> next = table[i];
                setOfKeys.add(next.key);
                while (next.getNext() != null) {
                    next = next.getNext();
                    setOfKeys.add(next.key);
                }
            }
        }
        return setOfKeys;
    }

    @Override
    public Collection<V> values() {
        Set<V> setOfValues = new HashSet<>();
        for (Node<K, V> n : table) {
            if (n != null) {
                setOfValues.add(n.getValue());
                while (n.getNext() != null) {
                    n = n.getNext();
                    setOfValues.add(n.getValue());
                }
            }
        }
        return setOfValues;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }
}
