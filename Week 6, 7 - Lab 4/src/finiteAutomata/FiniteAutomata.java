package finiteAutomata;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class FiniteAutomata {

    private final String filePath;
    private List<String> states;

    private List<String> alphabet;

    private String initialState;

    private List<String> finalStates;

    private final Map<TransitionFunction, Set<String>> transitionFunctions;

    private final boolean isDFA;

    public FiniteAutomata(String filePath) {
        this.transitionFunctions = new HashMap<>();

        this.filePath = filePath;
        this.readFromFile();

        this.isDFA = verifyDFA();
    }


    private void readFromFile() {
        try (Scanner scanner = new Scanner(new File(filePath))) {

            this.states = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            this.alphabet = new ArrayList<>(List.of(scanner.nextLine().split(",")));
            this.initialState = scanner.nextLine();
            this.finalStates = new ArrayList<>(List.of(scanner.nextLine().split(",")));

            while (scanner.hasNextLine()) {

                String[] transitionFunctionElems = scanner.nextLine().split(",");

                TransitionFunction transitionFunctionInput = new TransitionFunction(transitionFunctionElems[0], transitionFunctionElems[1]);

                if (!this.transitionFunctions.containsKey(transitionFunctionInput)) {
                    Set<String> transitionFunctionValues = new HashSet<>();
                    transitionFunctionValues.add(transitionFunctionElems[2]);
                    this.transitionFunctions.put(transitionFunctionInput, transitionFunctionValues);
                } else {
                    this.transitionFunctions.get(transitionFunctionInput).add(transitionFunctionElems[2]);
                }


            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean verifyDFA() {
        return this.transitionFunctions.values().stream().allMatch(list -> list.size() <= 1);
    }


    public boolean verifySequence(String sequence) {
        if (!this.isDFA) {
            return false;
        }

        String currentState = this.initialState;
        String[] alphabetSymbolSequenceList = sequence.split("");

        for (String currentAlphabetSymbol : alphabetSymbolSequenceList) {

            TransitionFunction transitionFunction = new TransitionFunction(currentState, currentAlphabetSymbol);

            if (!this.transitionFunctions.containsKey(transitionFunction))
                return false;

            currentState = this.transitionFunctions.get(transitionFunction).iterator().next();
        }

        return this.finalStates.contains(currentState);
    }

    public List<String> getStates() {
        return states;
    }

    public List<String> getAlphabet() {
        return alphabet;
    }

    public String getInitialState() {
        return initialState;
    }

    public List<String> getFinalStates() {
        return finalStates;
    }

    public String getTransitionFunctionsToString() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<TransitionFunction, Set<String>> entry : transitionFunctions.entrySet()) {
            sb.append("δ(").append(entry.getKey().state()).append(", ").append(entry.getKey().alphabetSymbol()).append(") = ").append(entry.getValue()).append("\n");
        }

        return sb.toString();
    }

    public boolean isDFA() {
        return isDFA;
    }
}
