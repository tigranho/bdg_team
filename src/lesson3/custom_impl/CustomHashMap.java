package lesson3.custom_impl;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CustomHashMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, Cloneable, Serializable {

    private Node<K, V>[] buckets;
    private int size;
    private int capacity;
    private static final byte DEFAULT_CAPACITY = 16;
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private float loadFactor;

    protected CustomHashMap() {
        buckets = new Node[capacity = DEFAULT_CAPACITY];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public CustomHashMap(int capacity) {
        buckets = new Node[this.capacity = capacity];
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    public CustomHashMap(int capacity, float loadFactor) {
        this(capacity);
        this.loadFactor = loadFactor;
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
    public V put(K key, V value) {
        if (size >= (capacity * loadFactor)) grow(capacity * 2);
        Node<K, V> oldNode = null;
        V oldVal = null;
        if (key == null) {
            Node<K, V> node = buckets[0];
            if (node == null) {
                node = new Node<>(0, null, value);
                buckets[0] = node;
                size++;
                return null;
            } else {
                oldVal = node.value;
                node.value = value;
            }
        } else if (buckets[Math.abs(key.hashCode() % (capacity - 2)) + 1] != null) {
            oldNode = buckets[key.hashCode() % (capacity - 2) + 1];
            while (oldNode != null) {
                if (oldNode.key.equals(key) && oldNode.hashcode == key.hashCode()) {
                    oldVal = oldNode.value;
                    oldNode.value = value;
                    return oldVal;
                }
                if (oldNode.next == null) {
                    oldNode.next = new Node<>(key.hashCode(), key, value);
                    break;
                } else
                    oldNode = oldNode.next;
            }
        } else {
            oldNode = new Node<>(key.hashCode(), key, value);
            buckets[Math.abs(key.hashCode() % (capacity - 2)) + 1] = oldNode;

        }
        size++;
        return oldVal;
    }

    @Override
    public boolean containsValue(Object value) {
        V val = (V) value;
        for (Node<K, V> node : buckets) {
            while (node != null) {
                if (val == null && node.value == null ||
                        node.value != null && node.value.equals(val)) return true;
                node = node.next;
            }
        }
        return false;
    }

    @Override
    public boolean containsKey(Object k) {
        K key = (K) k;
        Node<K, V> nodeByKey = findNodeByKey(key);
        return nodeByKey != null;
    }

    @Override
    public V get(Object k) {
        K key = (K) k;
        Node<K, V> nodeByKey = findNodeByKey(key);
        return nodeByKey != null ? nodeByKey.value : null;
    }


    @Override
    public V remove(Object k) {
        K key = (K) k;
        V oldVal = null;
        if (key != null) {
            final int hashIndex = Math.abs(key.hashCode() % (capacity - 2)) + 1;
            Node<K, V> node = buckets[hashIndex];
            while (node != null) {
                if (node.key.equals(key) && node.hashcode == key.hashCode()) {
                    buckets[hashIndex] = (node.next != null && Math.abs(node.next.key.hashCode() % (capacity - 2)) + 1 != hashIndex) ? null : node.next;
                    oldVal = node.value;
                    node = null;
                    --size;
                    return oldVal;
                }
                node = node.next;
            }
        } else if (buckets[0] != null && buckets[0].key == null) {
            oldVal = buckets[0].value;
            buckets[0] = null;
            --size;
        }
        return oldVal;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("[");
        for (Node<K, V> node : buckets) {
            while (node != null) {
                sb.append(node.key).append("=").append(node.value);
                node = node.next;
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }


    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if (capacity * loadFactor - size < m.size()) grow(m.size());
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
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public V getOrDefault(Object key, V defaultValue) {
        return null;
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {

    }

    @Override
    public void replaceAll(BiFunction<? super K, ? super V, ? extends V> function) {

    }

    @Override
    public V putIfAbsent(K key, V value) {
        return null;
    }

    @Override
    public boolean remove(Object key, Object value) {
        return false;
    }

    @Override
    public boolean replace(K key, V oldValue, V newValue) {
        return false;
    }

    @Override
    public V replace(K key, V value) {
        return null;
    }

    @Override
    public V computeIfAbsent(K key, Function<? super K, ? extends V> mappingFunction) {
        return null;
    }

    @Override
    public V computeIfPresent(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V compute(K key, BiFunction<? super K, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    @Override
    public V merge(K key, V value, BiFunction<? super V, ? super V, ? extends V> remappingFunction) {
        return null;
    }

    private class Node<K, V> {
        private int hashcode;
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(int hashcode, K key, V value) {
            this.hashcode = hashcode;
            this.key = key;
            this.value = value;
        }
    }


    private void grow(int newLength) {
        Node<K, V>[] newBuckets = null;
        newLength = newLength > (capacity *= 2) ?
                (capacity = (int) (newLength + size + (capacity * loadFactor))) : newLength;
        newBuckets = new Node[newLength];
        int i = 0;
        for (Node<K, V> node : buckets) {
            if (node == null) continue;
            if (node.key == null) {
                newBuckets[0] = node;
                continue;
            }
            //rehashing process
            Node<K, V> colisionNode = null;
            while (node != null) {
                if (newBuckets[Math.abs(node.key.hashCode() % (capacity - 2)) + 1] == null) {
                    newBuckets[Math.abs(node.key.hashCode() % (capacity - 2)) + 1] = node;
                } else {
                    colisionNode = newBuckets[Math.abs(node.key.hashCode() % (capacity - 2)) + 1];
                    while (colisionNode.next != null) colisionNode = colisionNode.next;
                    colisionNode.next = node;
                }
                node = node.next;
            }

        }
        buckets = newBuckets;
    }

    private Node<K, V> findNodeByKey(K key) {
        if (key != null) {
            Node<K, V> node = buckets[Math.abs(key.hashCode() % (capacity - 2)) + 1];
            while (node != null) {
                if (node.key.equals(key) && node.hashcode == key.hashCode()) return node;
                node = node.next;
            }
        } else if (buckets[0] != null && buckets[0].key == null) return buckets[0];
        return null;
    }


    public static void main(String[] args) {
        CustomHashMap<String, Integer> customHashMap = new CustomHashMap<>(5);
        customHashMap.put("a", 1);
        customHashMap.put("b", 2);
        customHashMap.put("d", 8);
        customHashMap.put("c", 3);
        customHashMap.put(null, 5);
        customHashMap.put("d", 4);
        customHashMap.put("f", 5);
        customHashMap.put("g", 6);
        System.out.println(customHashMap + " size: " + customHashMap.size());
        System.out.println(customHashMap.get("z"));
        System.out.println(customHashMap.get("d"));
        System.out.println(customHashMap.get(null));
        customHashMap.put("h", 7);
        System.out.println(customHashMap + " size: " + customHashMap.size());
        System.out.println(customHashMap.containsKey("s"));
        System.out.println(customHashMap.containsKey("f"));
        System.out.println(customHashMap.containsValue(11));
        System.out.println(customHashMap.containsValue(7));
        System.out.println(customHashMap.remove(null));
        System.out.println(customHashMap.remove("null"));
        System.out.println(customHashMap.remove("a"));
        System.out.println(customHashMap.isEmpty());
        System.out.println(customHashMap + " size: " + customHashMap.size());
    }
}