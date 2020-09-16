package task;

import java.util.*;


public class CustomHashMap<K,V> implements Map<K,V> {

    private static  final int DEFAULT_INITIAL_CAPACITY = 16;
    private final Node<K,V>[] nodes;
    private int size = 0;

    public CustomHashMap() {
        nodes = new Node[DEFAULT_INITIAL_CAPACITY];
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
    public boolean containsKey(Object key) {
        int i = key.hashCode()% nodes.length;
        Node<K,V> temp = nodes[i];
        while(temp != null){
            if (temp.getKey().equals(key)){
                return true;
            }
            temp = temp.next;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object value) {
        for (Node<K,V> node:nodes) {
            Node<K,V> temp =node;
            while(temp != null){
                if (!temp.getValue().equals(value)) {
                    temp = temp.next;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        int i = key.hashCode()% nodes.length;
        Node<K,V> temp = nodes[i];
        while(temp != null){
            if (temp.getKey().equals(key)){
                return  temp.getValue();
            }
            temp = temp.next;
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int i = key.hashCode()% nodes.length;
        if (containsKey(key)) {
            Node<K,V> temp = nodes[i];
            while(temp != null){
                if (temp.getKey().equals(key)){
                    V val =  temp.getValue();
                    temp.setValue(value);
                    return val;
                }
                temp = temp.next;
            }
        }
        Node<K,V> temp = nodes[i];
        while(temp != null){
            if (temp.next == null){
                temp.next = new Node<>(key, value);
                size++;
                return null;
            }
            temp = temp.next;
        }
        nodes[i]= new Node<>(key, value);
        size++;

        return null;
    }

    @Override
    public V remove(Object key) {
        V value = null;
        int i = key.hashCode()% nodes.length;
        Node<K,V> temp = nodes[i];
        if (temp.getKey().equals(key) & temp.next == null){
            value =  temp.getValue();
            temp.key= null;
            temp.value = null;
        }
        while(temp.next != null){
            if (temp.next.getKey().equals(key)){
                value =  temp.next.getValue();
                temp.next = temp.next.next ;
            }
            temp = temp.next;
        }
        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {

        for (Entry<? extends K, ? extends V> n : m.entrySet()) {
            put(n.getKey(),n.getValue());
        }
    }


    @Override
    public void clear() {
        Arrays.fill(nodes, null);
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(Node<K,V> n : nodes){
            set.add( n.getKey());
        }

        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> v = new ArrayList<>();
        for(Node<K,V> n : nodes){
            v.add( n.getValue());
        }
        return v;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return new HashSet<>(Arrays.asList(nodes));
    }

    private static class Node<K,V> implements Map.Entry<K,V>{
        int hash;
        K key;
        V value;
        Node<K,V> next ;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            hash = hashCode();
        }


        public K getKey() {
            return key;
        }


        public V getValue() {
            return value;
        }


        public V setValue(V value) {
            this.value =  value;
            return null;
        }

        @Override
        public int hashCode() {
            hash=31;
            hash=hash*17+key.hashCode();
            hash=hash*17+value.hashCode();
            return hash;
        }


    }


}