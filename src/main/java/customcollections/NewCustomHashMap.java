package customcollections;

import java.util.*;

public class NewCustomHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V> {
    private int size;
    private static final int DEFAULT_CAPACITY = 16;
    private Node<K, V>[] table;
    private Set<Entry<K, V>> entrySet;

    protected NewCustomHashMap() {
        super();
        this.table = new Node[DEFAULT_CAPACITY];
        this.entrySet = new HashSet<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsValue(Object o) {
        boolean contains = false;
        if (entrySet == null) {
            return contains;
        }
        if (o == null) {
            outer:
            for (int i = 0; i < table.length; i++) {
                Node<K, V> node = table[i];
                if (node == null) {
                    continue outer;
                }
                while (node != null) {
                    if (node.value == null) {
                        contains = true;
                        break outer;
                    }
                    node = node.next;
                }
            }
        } else {
            outer:
            for (int i = 0; i < table.length; i++) {
                Node<K, V> node = table[i];
                if (node == null) {
                    continue outer;
                }
                while (node != null) {
                    if (String.valueOf(node.value).equals(String.valueOf((V) o))) {
                        contains = true;
                        break outer;
                    }
                    node = node.next;
                }
            }
        }
        return contains;
    }

    @Override
    public boolean containsKey(Object o) {
        if (entrySet == null) {
            return false;
        }
        Iterator iterator = entrySet.iterator();
        Entry entry;
        if (o == null) {
            while (iterator.hasNext()) {
                entry = (Entry) iterator.next();
                if (entry.getKey() == null) {
                    return true;
                }
            }
        } else {
            while (iterator.hasNext()) {
                entry = (Entry) iterator.next();
                if (entry.getKey().equals((K) o)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object o) {
        Iterator iterator = entrySet.iterator();
        Entry entry;
        if (o == null) {
            while (iterator.hasNext()) {
                entry = (Entry) iterator.next();
                if (entry.getKey() == null) {
                    return (V) entry.getValue();
                }
            }
        } else {
            while (iterator.hasNext()) {
                entry = (Entry) iterator.next();
                if (entry.getKey() != null && entry.getKey().equals((K) o)) {
                    return (V) entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public V put(K k, V v) {
        int hash = hash(k);
        Node<K, V> newNode = new Node<>(hash, k, v, null);
        Node<K, V> oldNode = table[hash];
        Node<K, V> current;
        V oldValue = null;
        if (oldNode == null) {
            table[hash] = newNode;
            entrySet.add(newNode);
            size++;
        } else {
            if (!oldNode.equals(newNode)) {
                current = oldNode;
                while (current != null) {
                    if (String.valueOf(current.key).equals(k)) {
                        oldValue = current.setValue(v);
                        break;
                    }
                    current = current.next;
                    if (current == null) {
                        current = newNode;
                        entrySet.add(current);
                        size++;
                        break;
                    }
                }
            } else {
                oldValue = oldNode.setValue(v);
            }
        }
        return oldValue;
    }

    @Override
    public V remove(Object o) {
        return super.remove(o);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        if (map == null) {
            throw new NullPointerException("passed map can't be null");
        }
        for (Entry<? extends K, ? extends V> m : map.entrySet()) {
            put(m.getKey(), m.getValue());
        }
    }

    @Override
    public void clear() {
        super.clear();
    }

    @Override
    public Set<K> keySet() {
        return super.keySet();
    }

    @Override
    public Collection<V> values() {
        return super.values();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        Iterator iterator = entrySet.iterator();
        int count = 0;
        while (iterator.hasNext()) {
            count++;
            Map.Entry<K, V> entry = (Map.Entry<K, V>) iterator.next();
            stringBuilder.append(entry.getKey() + "=" + entry.getValue());
            if (count != size) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(entrySet);
    }

    static final int hash(Object o) {
//        int h;
//        return o== null ? 0 : (h = o.hashCode()) ^ h >>> 16;
        return o == null ? 0 : o.hashCode() % DEFAULT_CAPACITY;
    }

    private static class Node<K, V> implements Entry<K, V> {
        final int hashCode;
        final K key;
        V value;
        NewCustomHashMap.Node<K, V> next;

        Node(int hashCode, K k, V v, NewCustomHashMap.Node<K, V> next) {
            this.hashCode = hashCode;
            this.key = k;
            this.value = v;
            this.next = next;
        }

        @Override
        public final K getKey() {
            return this.key;
        }

        @Override
        public final V getValue() {
            return this.value;
        }

        @Override
        public final V setValue(V v) {
            Object old = this.value;
            this.value = v;
            return (V) old;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            } else {
                if (o instanceof Entry) {
                    Entry<K, V> entry = (Entry<K, V>) o;
                    if (String.valueOf(this.key).equals(String.valueOf(entry.getKey()))
                            && String.valueOf(this.value).equals(String.valueOf(entry.getValue()))) {
                        return true;
                    }
                }
            }
            return false;
//            return super.equals(o);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public String toString() {
            return this.key + "=" + this.value;
        }
    }
}
