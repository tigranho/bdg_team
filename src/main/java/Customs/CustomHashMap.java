package Customs;

import java.util.*;

public class CustomHashMap<K, V> implements Map<K, V> {

    /**
     * Basic hash bin node, used for most entries.
     */
    static class Node<K, V> {
        final int hash;
        final K key;
        V value;
        Node<K, V> next;

        Node(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        public final boolean equals(Object o) {
            if(o == this) {
                return true;
            }
            if(o instanceof Node) {
                Node<?, ?> obj = (Node<?, ?>) o;
                return Objects.equals(key, obj.key) && Objects.equals(value, obj.value);
            }
            return false;
        }
    }

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4;

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * Computes key.hashCode() and spreads (XORs) higher bits of hash
     * to lower.  Because the table uses power-of-two masking, sets of
     * hashes that vary only in bits above the current mask will
     * always collide. (Among known examples are sets of Float keys
     * holding consecutive whole numbers in small tables.)  So we
     * apply a transform that spreads the impact of higher bits
     * downward. There is a tradeoff between speed, utility, and
     * quality of bit-spreading. Because many common sets of hashes
     * are already reasonably distributed (so don't benefit from
     * spreading), and because we use trees to handle large sets of
     * collisions in bins, we just XOR some shifted bits in the
     * cheapest possible way to reduce systematic lossage, as well as
     * to incorporate impact of the highest bits that would otherwise
     * never be used in index calculations because of table bounds.
     */
    static int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    /**
     * The table, initialized on first use, and resized as
     * necessary. When allocated, length is always a power of two.
     */
    private Node<K, V>[] table;

    /**
     * The number of key-value mappings contained in this map.
     */
    private int size;

    /**
     * The load factor for the hash table.
     *
     * @serial
     */
    final float loadFactor;

    /**
     * Constructs an empty <tt>HashMap</tt> with the default initial capacity
     * (16) and the default load factor (0.75).
     */
    @SuppressWarnings("unchecked")
    public CustomHashMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
        size = 0;
        loadFactor = DEFAULT_LOAD_FACTOR;
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and load factor.
     *
     * @param  initialCapacity the initial capacity
     * @param  loadFactor      the load factor
     * @throws IllegalArgumentException if the initial capacity is negative
     *         or the load factor is nonpositive
     */
    @SuppressWarnings("unchecked")
    public CustomHashMap(int initialCapacity, float loadFactor) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal initial capacity: " + initialCapacity);
        if (initialCapacity > MAXIMUM_CAPACITY)
            initialCapacity = MAXIMUM_CAPACITY;
        if (loadFactor <= 0 || Float.isNaN(loadFactor))
            throw new IllegalArgumentException("Illegal load factor: " + loadFactor);
        table = new Node[initialCapacity];
        size = 0;
        this.loadFactor = loadFactor;
    }

    /**
     * Constructs an empty <tt>HashMap</tt> with the specified initial
     * capacity and the default load factor (0.75).
     *
     * @param  initialCapacity the initial capacity.
     * @throws IllegalArgumentException if the initial capacity is negative.
     */
    public CustomHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_LOAD_FACTOR);
    }

    /**
     * Returns the number of key-value mappings in this map.  If the
     * map contains more than <tt>Integer.MAX_VALUE</tt> elements, returns
     * <tt>Integer.MAX_VALUE</tt>.
     *
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns <tt>true</tt> if this map contains no key-value mappings.
     *
     * @return <tt>true</tt> if this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns <tt>true</tt> if this map contains a mapping for the specified
     * key.  More formally, returns <tt>true</tt> if and only if
     * this map contains a mapping for a key <tt>k</tt> such that
     * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
     * at most one such mapping.)
     *
     * @param key key whose presence in this map is to be tested
     * @return <tt>true</tt> if this map contains a mapping for the specified
     * key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsKey(Object key) {
        Node<K, V> e = getNode(key);
        return e != null;
    }

    /**
     * Returns <tt>true</tt> if this map maps one or more keys to the
     * specified value.  More formally, returns <tt>true</tt> if and only if
     * this map contains at least one mapping to a value <tt>v</tt> such that
     * <tt>(value==null ? v==null : value.equals(v))</tt>.  This operation
     * will probably require time linear in the map size for most
     * implementations of the <tt>Map</tt> interface.
     *
     * @param value value whose presence in this map is to be tested
     * @return <tt>true</tt> if this map maps one or more keys to the
     * specified value
     * @throws ClassCastException   if the value is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified value is null and this
     *                              map does not permit null values
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean containsValue(Object value) {
        if (table != null && size > 0) {
            for (Node<K, V> node : table) {
                for (Node<K, V> e = node; e != null; e = e.next) {
                    if (Objects.equals(value, e.value))
                        return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped,
     * or {@code null} if this map contains no mapping for the key.
     *
     * <p>More formally, if this map contains a mapping from a key
     * {@code k} to a value {@code v} such that {@code (key==null ? k==null :
     * key.equals(k))}, then this method returns {@code v}; otherwise
     * it returns {@code null}.  (There can be at most one such mapping.)
     *
     * <p>If this map permits null values, then a return value of
     * {@code null} does not <i>necessarily</i> indicate that the map
     * contains no mapping for the key; it's also possible that the map
     * explicitly maps the key to {@code null}.  The {@link #containsKey
     * containsKey} operation may be used to distinguish these two cases.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or
     * {@code null} if this map contains no mapping for the key
     * @throws ClassCastException   if the key is of an inappropriate type for
     *                              this map
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key is null and this map
     *                              does not permit null keys
     *                              (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V get(Object key) {
        Node<K, V> e = getNode(key);
        return (e == null) ? null : e.value;
    }

    /**
     * Implements Map.get and related methods.
     * @param key the key
     * @return the node, or null if none
     */
    private Node<K, V> getNode(Object key) {
        if(table != null && size > 0) {
            int hash = hash(key);
            Node<K, V> current = table[hash & (table.length - 1)];
            for (; current != null; current = current.next) {
                if (Objects.equals(key, current.key)) {
                    return current;
                }
            }
        }
        return null;
    }

    /**
     * Associates the specified value with the specified key in this map
     * (optional operation).  If the map previously contained a mapping for
     * the key, the old value is replaced by the specified value.  (A map
     * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
     * if {@link #containsKey(Object) m.containsKey(k)} would return
     * <tt>true</tt>.)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * (A <tt>null</tt> return can also indicate that the map
     * previously associated <tt>null</tt> with <tt>key</tt>,
     * if the implementation supports <tt>null</tt> values.)
     * @throws UnsupportedOperationException if the <tt>put</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of the specified key or value
     *                                       prevents it from being stored in this map
     * @throws NullPointerException          if the specified key or value is null
     *                                       and this map does not permit null keys or values
     * @throws IllegalArgumentException      if some property of the specified key
     *                                       or value prevents it from being stored in this map
     */
    @Override
    public V put(K key, V value) {
        int i = (table.length - 1) & hash(key);
        V oldValue = null;
        Node<K, V> toPut = new Node<>(hash(key), key, value);
        if(table == null || table.length == 0) {
            resize();
        }
        if(table[i] == null) {
            table[i] = toPut;
        }
        else {
            Node<K, V> current = table[i];
            while(current.next != null && current.key != key) {
                current = current.next;
            }
            if(current.key == key) {
                oldValue = current.value;
                current.value = value;
            }
            else {
                current.next = toPut;
            }
        }
        if(size > table.length * loadFactor) {
            resize();
        }
        ++size;

        return oldValue;
    }

    /**
     * Initializes or doubles table size.  If null, allocates in
     * accord with initial capacity target held in field threshold.
     * Otherwise, because we are using power-of-two expansion, the
     * elements from each bin must either stay at same index, or move
     * with a power of two offset in the new table.
     */
    @SuppressWarnings("unchecked")
    private void resize() {
        int newCap = (table == null || table.length == 0) ? DEFAULT_INITIAL_CAPACITY : table.length << 1;
        Node<K,V>[] newTab = new Node[newCap];
        int i = 0, newSize = 0;
        if(table != null) {
            while (newSize != size) {
                if (table[i] != null) {
                    int index = (newCap - 1) & hash(table[i].key);
                    if (newTab[index] == null) {
                        newTab[index] = table[i];
                    } else {
                        Node<K, V> current = newTab[index];
                        while (current.next != null) {
                            current = current.next;
                        }
                        current.next = table[i];
                    }
                    ++newSize;
                }
                ++i;
            }
        }
        table = newTab;
    }

    /**
     * Removes the mapping for a key from this map if it is present
     * (optional operation).   More formally, if this map contains a mapping
     * from key <tt>k</tt> to value <tt>v</tt> such that
     * <code>(key==null ?  k==null : key.equals(k))</code>, that mapping
     * is removed.  (The map can contain at most one such mapping.)
     *
     * <p>Returns the value to which this map previously associated the key,
     * or <tt>null</tt> if the map contained no mapping for the key.
     *
     * <p>If this map permits null values, then a return value of
     * <tt>null</tt> does not <i>necessarily</i> indicate that the map
     * contained no mapping for the key; it's also possible that the map
     * explicitly mapped the key to <tt>null</tt>.
     *
     * <p>The map will not contain a mapping for the specified key once the
     * call returns.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with <tt>key</tt>, or
     * <tt>null</tt> if there was no mapping for <tt>key</tt>.
     * @throws UnsupportedOperationException if the <tt>remove</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the key is of an inappropriate type for
     *                                       this map
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified key is null and this
     *                                       map does not permit null keys
     *                                       (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public V remove(Object key) {
        int index = (table.length - 1) & hash(key);
        if(table != null && table[index] != null) {
            if(table[index].key == key) {
                V oldValue = table[index].value;
                table[index] = table[index].next;
                return oldValue;
            }
            else {
                Node<K, V> current = table[index];
                while(current.next != null && current.next.key != key) {
                    current = current.next;
                }
                if(current.next == null) {
                    return null;
                }
                V oldValue = current.value;
                current.next = current.next.next;
                return oldValue;
            }
        }
        return null;
    }

    /**
     * Copies all of the mappings from the specified map to this map
     * (optional operation).  The effect of this call is equivalent to that
     * of calling {@link #put(Object, Object) put(k, v)} on this map once
     * for each mapping from key <tt>k</tt> to value <tt>v</tt> in the
     * specified map.  The behavior of this operation is undefined if the
     * specified map is modified while the operation is in progress.
     *
     * @param m mappings to be stored in this map
     * @throws UnsupportedOperationException if the <tt>putAll</tt> operation
     *                                       is not supported by this map
     * @throws ClassCastException            if the class of a key or value in the
     *                                       specified map prevents it from being stored in this map
     * @throws NullPointerException          if the specified map is null, or if
     *                                       this map does not permit null keys or values, and the
     *                                       specified map contains null keys or values
     * @throws IllegalArgumentException      if some property of a key or value in
     *                                       the specified map prevents it from being stored in this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        if(m == null) {
            throw new NullPointerException("The given map is null.");
        }
        for(Entry<? extends K, ? extends V> el : m.entrySet()) {
            this.put(el.getKey(), el.getValue());
        }
    }

    /**
     * Removes all of the mappings from this map (optional operation).
     * The map will be empty after this call returns.
     *
     * @throws UnsupportedOperationException if the <tt>clear</tt> operation
     *                                       is not supported by this map
     */
    @Override
    public void clear() {
        for(int i = 0; i < table.length; ++i) {
            if(table[i] != null) {
                Node<K, V> current = table[i], next;
                while(current != null) {
                    next = current.next;
                    current.next = null;
                    current = next;
                }
                table[i] = null;
            }
        }
        size = 0;
    }

    /**
     * Returns a {@link Set} view of the keys contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation), the results of
     * the iteration are undefined.  The set supports element removal,
     * which removes the corresponding mapping from the map, via the
     * <tt>Iterator.remove</tt>, <tt>Set.remove</tt>,
     * <tt>removeAll</tt>, <tt>retainAll</tt>, and <tt>clear</tt>
     * operations.  It does not support the <tt>add</tt> or <tt>addAll</tt>
     * operations.
     *
     * @return a set view of the keys contained in this map
     */

    //To do
    @Override
    public Set<K> keySet() {
        return new HashSet<>();
    }

    /**
     * Returns a {@link Collection} view of the values contained in this map.
     * The collection is backed by the map, so changes to the map are
     * reflected in the collection, and vice-versa.  If the map is
     * modified while an iteration over the collection is in progress
     * (except through the iterator's own <tt>remove</tt> operation),
     * the results of the iteration are undefined.  The collection
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Collection.remove</tt>, <tt>removeAll</tt>,
     * <tt>retainAll</tt> and <tt>clear</tt> operations.  It does not
     * support the <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a collection view of the values contained in this map
     */

    //To do
    @Override
    public Collection<V> values() {
        return new HashSet<>();
    }

    /**
     * Returns a {@link Set} view of the mappings contained in this map.
     * The set is backed by the map, so changes to the map are
     * reflected in the set, and vice-versa.  If the map is modified
     * while an iteration over the set is in progress (except through
     * the iterator's own <tt>remove</tt> operation, or through the
     * <tt>setValue</tt> operation on a map entry returned by the
     * iterator) the results of the iteration are undefined.  The set
     * supports element removal, which removes the corresponding
     * mapping from the map, via the <tt>Iterator.remove</tt>,
     * <tt>Set.remove</tt>, <tt>removeAll</tt>, <tt>retainAll</tt> and
     * <tt>clear</tt> operations.  It does not support the
     * <tt>add</tt> or <tt>addAll</tt> operations.
     *
     * @return a set view of the mappings contained in this map
     */

    //To do
    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>();
    }
}
