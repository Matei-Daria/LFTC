package scanner;

import programInternalForm.ProgramInternalForm;
import programInternalForm.ProgramInternalFormPair;
import symbolTable.HashTablePosition;
import symbolTable.SymbolTable;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class MyScanner {

    private ArrayList<String> operators;

    private ArrayList<String> separators;

    private ArrayList<String> keywords;

    private ArrayList<String> alphabetStartingChars;

    private ArrayList<String> alphabetNonStartingChars;

    private String alphabetStartingCharsString;

    private String alphabetNonStartingCharsString;

    private final String programFileName;

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    private final SymbolTable symbolTable;

    public ProgramInternalForm getProgramInternalForm() {
        return programInternalForm;
    }

    private final ProgramInternalForm programInternalForm;

    public MyScanner(String programFileName) {
        this.operators = new ArrayList<>();
        this.separators = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.alphabetStartingChars = new ArrayList<>();
        this.alphabetNonStartingChars = new ArrayList<>();
        this.programFileName = programFileName;
        this.symbolTable = new SymbolTable(10);
        this.programInternalForm = new ProgramInternalForm();
    }

    private String readProgramFromFile() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        Scanner scanner = new Scanner(new File(this.programFileName));
        while (scanner.hasNextLine()) {
            sb.append(scanner.nextLine()).append("\n");
        }
        return sb.toString().replace("\t", "");
    }

    private void readLanguageTokens() {
        try {
            Scanner scanner = new Scanner(new File("io/token.in"));
            List<String> currentList = operators;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                if (line.equals("Separators")) {
                    currentList = separators;
                } else if (line.equals("Keywords")) {
                    currentList = keywords;
                } else if (line.equals("Alphabet")) {
                    currentList = alphabetStartingChars; // this list contains the part of the alphabet
                    // that can represent the starting char (first letter) of an identifier
                } else if (line.equals("Alphabet (non starting)")) {
                    currentList = alphabetNonStartingChars; // in this list, all the alphabet will be added
                } else {
                    currentList.add(line);
                }
            }

            alphabetNonStartingChars.addAll(alphabetStartingChars); // we add the remaining letters
            buildAlphabetStrings(); // from the newly created alphabet list of Strings,
                                    // create the corresponding Strings of all elements concatenated (for the regex)


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private List<String> getListOfSeparatedStrings() {
        try {
            String content = this.readProgramFromFile();

            readLanguageTokens();
            String separatorsConcatenated = this.separators.stream().reduce(" \n", (a, b) -> (a + b));
            StringTokenizer stringTokenizer = new StringTokenizer(content, separatorsConcatenated, true);

            return Collections.list(stringTokenizer)
                    .stream()
                    .map(t -> (String) t)
                    .collect(Collectors.toList());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }

    private List<TokenLinePair> getListOfTokenLinePairs() {

        List<String> tokenStringsList = getListOfSeparatedStrings();
        if (tokenStringsList == null) {
            return null;
        }

        List<TokenLinePair> tokenLinePairsList = new ArrayList<>();

        int line = 1;
        boolean parsingString = false;
        StringBuilder parsedString = new StringBuilder();

        for (String tokenString : tokenStringsList) {

            if (tokenString.equals("\"")) {  // end or beginning of string const
                parsedString.append(tokenString);
                if (parsingString) {
                    tokenLinePairsList.add(new TokenLinePair(parsedString.toString(), line));
                    parsedString = new StringBuilder();
                }
                parsingString = !parsingString;

            } else if (tokenString.equals("\n")) {  // end of program line
                line++;

            } else if (parsingString) {  // we are parsing a string const (not at the beginning or end)
                // we need to add the token to the currently parsed string var
                parsedString.append(tokenString);

            } else if (!tokenString.equals(" ")) { // any other token besides space
                tokenLinePairsList.add(new TokenLinePair(tokenString, line));
            }
        }

        return tokenLinePairsList;
    }

    private void buildAlphabetStrings() {
        this.alphabetStartingCharsString = this.alphabetStartingChars.stream().reduce("", (a, b) -> (a + b));
        this.alphabetNonStartingCharsString = this.alphabetNonStartingChars.stream().reduce("", (a, b) -> (a + b));
    }


    public void scan() {

        List<TokenLinePair> tokens = getListOfTokenLinePairs();
        AtomicBoolean lexicalErrorExists = new AtomicBoolean(false);

        assert tokens != null;
        tokens.forEach(tokenLinePair -> {
            String token = tokenLinePair.token();
            if (this.operators.contains(token) || this.separators.contains(token) || this.keywords.contains(token)) {
                this.programInternalForm.add(new ProgramInternalFormPair(token, new HashTablePosition(-1, -1)));
            } else if (Pattern.compile("^0|[-|+][1-9]([0-9])*|'[1-9]'|'[a-zA-Z]'|\"[0-9]*[a-zA-Z ]*\"|\"[0-9]*[a-zA-Z !@#%&]*\"$").matcher(token).matches()) {
                this.symbolTable.add(token);
                this.programInternalForm.add(new ProgramInternalFormPair("CONST", this.symbolTable.getSymbolTablePosition(token)));
            } else if (Pattern.compile("^[" + alphabetStartingCharsString + "][" + alphabetStartingCharsString + alphabetNonStartingCharsString + "]*$").matcher(token).matches()) {
                this.symbolTable.add(token);
                this.programInternalForm.add(new ProgramInternalFormPair("ID", this.symbolTable.getSymbolTablePosition(token)));
            } else {
                System.out.println("Lexical error at line " + tokenLinePair.line() + " at token " + token);
                lexicalErrorExists.set(true);
            }
        });

        if (!lexicalErrorExists.get()) {
            System.out.println("Lexically correct");
        }

    }

}
