package programInternalForm;

import symbolTable.HashTablePosition;

public record ProgramInternalFormPair(String token, HashTablePosition position) {

    @Override
    public String toString() {
        return "(" + token + "," + position + ")";
    }
}
