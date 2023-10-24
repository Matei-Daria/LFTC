import java.util.ArrayList;
import java.util.List;

public class HashTable {
    private final int tableSize;
    private final List<List<String>> buckets;

    public HashTable(int size) {
        this.tableSize = size;
        this.buckets = new ArrayList<>(this.tableSize);
        for (int i = 0; i < this.tableSize; i++) {
            this.buckets.add(new ArrayList<>());
        }
    }

    private int hash(String value) {
        return this.getAsciiSumOfChars(value) % tableSize;
    }

    public boolean add(String value) {
        if (this.contains(value))
            return false;

        int hash = this.hash(value);
        List<String> bucketElements = this.buckets.get(hash);
        bucketElements.add(value);

        return true;
    }

    public void remove(String value) {
        if (this.contains(value)) {
            List<String> elements = this.buckets.get(this.hash(value));
            elements.remove(getIndexInBucket(value));
        }
    }

    public boolean contains(String value) {
        int hash = hash(value);

        if (buckets.get(hash).isEmpty())
            return false;

        List<String> elements = this.buckets.get(hash);
        for (int col = 0; col < elements.size(); col++) {
            if (elements.get(col).equals(value)) {
                return true;
            }
        }

        return false;
    }

    public int getIndexInBucket(String value) {
        int hash = hash(value);

        List<String> elements = this.buckets.get(hash);
        for (int col = 0; col < elements.size(); col++) {
            if (elements.get(col).equals(value)) {
                return col;
            }
        }

        return -1;
    }

    public int getAsciiSumOfChars(String value) {
        int asciiSum = 0;
        char[] keyCharacters = value.toCharArray();
        for (char c : keyCharacters) {
            asciiSum += c;
        }
        return asciiSum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size of table: ").append(tableSize).append("\n");

        for (int i = 0; i < tableSize; i++) {
            sb.append("Bucket ").append(i).append(" : ");
            List<String> elements = buckets.get(i);
            for (int j = 0; j < elements.size(); j++) {
                sb.append(elements.get(j));
                if (j < elements.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("\n");
        }

        return sb.toString();
    }


    public int getTableSize() {
        return tableSize;
    }

    public List<List<String>> getBuckets() {
        return buckets;
    }
}
