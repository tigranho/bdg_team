package task1;

public class CustomHashMap<K, V> {

    private int capacity = 16;
    private Entry<K, V>[] table;

    class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        public Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }

    public CustomHashMap() {
        table = new Entry[capacity];
    }

    public void put(K key, V value) {
        int hash = hash(key);
        Entry<K, V> newEntry = new Entry<K, V>(key, value, null);
        if (table[hash] == null) {
            table[hash] = newEntry;
        } else {
            Entry<K, V> previous = null;
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    break;
                }
                previous = current;
                current = current.getNext();
            }
            if (previous != null) {
                previous.setNext(newEntry);
            }
        }
    }

    public V get(K key) {
        V value = null;
        int hash = hash(key);
        Entry<K, V> entry = table[hash];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                value = entry.getValue();
                break;
            }
            entry = entry.getNext();
        }
        return value;
    }

    public void remove(K key) {
        int hash = hash(key);
        Entry previous = null;
        Entry entry = table[hash];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (previous == null) {
                    entry = entry.getNext();
                    table[hash] = entry;
                    break;
                } else {
                    previous.setNext(entry.getNext());
                    break;
                }
            }
            previous = entry;
            entry = entry.getNext();
        }
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> current = table[i];
                while (current != null) {
                    System.out.println(current.getKey() + " " + current.getValue());
                    current = current.getNext();
                }
            }
        }
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        return Math.abs(key.hashCode() % capacity);
    }
}
