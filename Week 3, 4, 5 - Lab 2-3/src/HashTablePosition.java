public record HashTablePosition(int bucket, int indexInBucket) {

    @Override
    public String toString() {
        return bucket == -1 ? "0" : "(" + bucket + ", " + indexInBucket + ")";
    }
}
