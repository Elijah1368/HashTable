public class MyHashTable<K extends Comparable<K>, V> {
    private Integer capacity;
    private K[] keyBuckets;
    private V[] valueBuckets;
    private Integer size;
    public MyArrayList<K> keys;
    public Integer comparisons;
    public Integer maxProbe;

    public MyHashTable(Integer capacity) {
        this.capacity = capacity;
        this.keyBuckets = (K[]) new Comparable[capacity];
        this.valueBuckets = (V[]) new Object[capacity];
        this.size = 0;
        this.keys = new MyArrayList<K>(capacity);
        this.comparisons = 0;
        this.maxProbe = 0;
    }

    private Integer hash(K key) {
        return Math.abs(key.hashCode()) % this.capacity;
    }

    public V get(K key) {
        Integer index = hash(key);
    
        while (keyBuckets[index] != null) {
            if (keyBuckets[index].equals(key)) {
                return valueBuckets[index];
            }
            index = (index + 1) % capacity;
          
        }
        return null;
    }

    public void put(K key, V value) {
        Integer index = hash(key);
        Integer probe = 1;

        while (keyBuckets[index] != null) {
            if (keyBuckets[index].compareTo(key) == 0) {
                valueBuckets[index] = value;
                return;
            }
            index = (index + 1) % capacity;
            probe++;
        }

        keyBuckets[index] = key;
        valueBuckets[index] = value;
        keys.insert(key, size);
        size++;

        comparisons += probe;

        if (probe > maxProbe) {
            maxProbe = probe;
        }
    }

    public Integer size(){
        return this.size;
    }

    public String toString(){
        if (this.size == 0) return "[]";

        StringBuilder hashTable = new StringBuilder("[");

        
        for (int i = 0; i < keyBuckets.length; i++) {
            if (keyBuckets[i] != null) {
                K key = keyBuckets[i];
            
                if (i != 0) hashTable.append(", ");
                
                hashTable.append(key);
                hashTable.append(":");
                
                hashTable.append(get(key));
            }
        }

        hashTable.append("]");

        return hashTable.toString();
    }
}
