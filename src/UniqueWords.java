
public class UniqueWords {

    private BookReader book;

    public UniqueWords() {
        book = new BookReader("WarAndPeace.txt");
        addUniqueWordsToHashTable();
    }

    public void addUniqueWordsToHashTable() {
        //Resets pointer to first word
        book.words.first();
        MyHashTable<String, Integer> myHashTable = new MyHashTable<>(32768);

        //Times adding unique words
        long start = System.currentTimeMillis();

        String currWord = book.words.current();

        while (currWord != null) {
            Integer val = myHashTable.get(currWord);
            if (val == null) {
                myHashTable.put(currWord, 1);
            } else {
                myHashTable.put(currWord, val + 1);
            }
            currWord = book.words.next();
        }

        long time = System.currentTimeMillis() - start;
        System.out.printf("Adding unique words to a hash table.. in %d milliseconds%n", time);
        System.out.printf("%d unique words%n", myHashTable.size());
        System.out.printf("%d comparisons%n", myHashTable.comparisons);
        System.out.printf("%d max probe%n", myHashTable.maxProbe);
        //Times sorting unique words
        start = System.currentTimeMillis();
        myHashTable.toString();
        time = System.currentTimeMillis() - start;

        System.out.printf("Extracting the key-value pairs... in %d milliseconds%n%n", time);
        //System.out.println(linkedListUnique);
    }
   
}
