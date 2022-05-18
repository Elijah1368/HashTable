import java.util.Arrays;

public class MyArrayList<T extends Comparable<T>> {
    public long comparisons;
    private T[] list;
    private int capacity;
    private int size;
    
    public MyArrayList(){
        this.list = (T[]) new Comparable[16];
        this.capacity = 16;
        this.size = 0;
        comparisons = 0;
    } 

    public MyArrayList(int capacity){
        this.list = (T[]) new Comparable[capacity];
        this.capacity = capacity;
        this.size = 0;
        comparisons = 0;
    } 

    public MyArrayList(T[] elements) {
        this.list = (T[]) elements;
        if (elements.length > 16) {
            this.capacity = elements.length;
        } else {
            this.capacity = 16;
        }
        this.size = elements.length;
        comparisons = 0;
    }

    public void insert(T element, int index) {
        if (index > size || index < 0) return;
        if (this.capacity < size + 1) resize();
        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        size += 1;
        list[index] = element;
    }

    public T remove(int index) {
        if (index >= size || index < 0) return null;
        T removedObj = list[index];
        for (int i = index; i < size; i++) {
            list[i] = list[i + 1];
        }
        size -= 1;
        return removedObj;
    }

    public boolean contains(T element){
        comparisons += 1;
        for (int i = 0; i < size; i++) {
            comparisons += 1;
            if (element.compareTo(list[i]) == 0) {
                return true;
            }
            
        }
        return false;
    }

    public void sort() {
        if (size <= 1) return;
        quicksort(0, size - 1);
    }

    private void quicksort(int left, int right){
        if (left >= right) {
            return;
        }
        T pivot = list[(left + right) / 2];
        int part = partition(left, right, pivot);
        quicksort(left, part - 1);
        quicksort(part, right);
    }

    private int partition(int left, int right, T pivot) {
        while (left <= right) {
            while (list[left].compareTo(pivot) < 0) {
                left++;
            }
            while (list[right].compareTo(pivot) > 0) {
                right--;
            }

            if (left <= right) {
                swap(left, right);
                left++;
                right--;
            }
        }
        return left;
    }

    private void swap(int a, int b){
        T temp = list[a];
        list[a] = list[b];
        list[b] = temp;
    }

    public int indexOf(T element){
        for (int i = 0; i < size; i++) {
            if (element.equals(list[i])) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index){
        if (index >= size || index < 0)  return null;
        return list[index];
    }

    public void set(int index, T elem){
        if (index >= size || index < 0)  return;
        list[index] = elem;
    }

    public int size(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(this.list, 0, size));
    }

    private void resize(){
        int newCapacity = this.capacity * 2;
        T[] newArr = (T[]) new Comparable[newCapacity];
        for (int i = 0; i < size; i ++) {
            newArr[i] = list[i];
        }
        list = newArr;
        this.capacity = newCapacity;
    }
}
