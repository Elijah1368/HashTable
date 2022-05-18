public class App {
    public static void main(String[] args) {
        new UniqueWords();
        MyHashTable<Integer, String> test = new MyHashTable<>(5);
        test.put(1, "asd");
        System.out.println(test.maxProbe);
    }
}
