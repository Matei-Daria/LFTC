public class SymbolTable {
    private final HashTable hashTable;

    public SymbolTable(Integer size) {
        hashTable = new HashTable(size);
    }

    public boolean add(String value) {
        return hashTable.add(value);
    }

    public void remove(String value) {
        hashTable.remove(value);
    }

    public boolean contains(String value) {
        return hashTable.contains(value);
    }

    public HashTablePosition getSymbolTablePosition(String value) {
        return hashTable.getPosition(value);
    }

    @Override
    public String toString() {
        return hashTable.toString();
    }
}
