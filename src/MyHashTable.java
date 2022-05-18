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
        int hashCode = hash(key);
  
        for (int i = 0; i < this.capacity; i++) {
            int index = (hashCode + i) % this.capacity;  

            if (keyBuckets[index] == null) {
                return null;
            }
            
            if (keyBuckets[index].compareTo(key) == 0) {
                return valueBuckets[index];
            }
       }

        return null;
    }

    public void put(K key, V value) {
        int hashCode = hash(key);
        int index = hashCode;
        
        int i = 0;
        for (; i < this.capacity; i++) {
            index = (hashCode + i) % this.capacity;    

            if (keyBuckets[index] == null || keyBuckets[index].compareTo(key) == 0) {  
                
                if (keyBuckets[index] == null) {
                    keys.insert(key, this.size);
                    this.size++;
                }

                maxProbe = Math.max(maxProbe, i);
                //comparisons += i;
                keyBuckets[index] = key;
                valueBuckets[index] = value;
                return;
            }
            comparisons++;
        }

        //comparisons += i - 1;
        maxProbe = Math.max(maxProbe, i - 1);
    }

    public int size(){
        return this.size;
    }

    public String toString(){
        if (this.size == 0) return "[]";

        StringBuilder hashTable = new StringBuilder("[");
        K key = keys.get(0);
        hashTable.append(key);
        hashTable.append(":");
        hashTable.append(get(key));
        
        for (int i = 1; i < keys.size(); i++) {
            hashTable.append(", ");
            key = keys.get(i);
            hashTable.append(key);
            hashTable.append(":");
            hashTable.append(get(key));
        }

        hashTable.append("]");

        return hashTable.toString();
    }
}
