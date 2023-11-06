import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {
    private List<ProgramInternalFormPair> arrayOfPairs;

    public ProgramInternalForm() {
        this.arrayOfPairs = new ArrayList<>();
    }

    public void add(ProgramInternalFormPair programInternalFormPair) {
        this.arrayOfPairs.add(programInternalFormPair);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Size of PIF: ").append(arrayOfPairs.size()).append("\n");

        for (ProgramInternalFormPair pair : arrayOfPairs)
            sb.append(pair).append("\n");

        return sb.toString();
    }
}
