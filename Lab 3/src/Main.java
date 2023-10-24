public class Main {
    public static void main(String[] args) {
        SymbolTable symbolTable = new SymbolTable(5);

        symbolTable.add("a");
        symbolTable.add("2");
        symbolTable.add("100");
        symbolTable.add("x");
        boolean check = symbolTable.add("c");
        boolean check2 = symbolTable.add("c");

        System.out.println(symbolTable);
        System.out.println("Symbol table contains identifier y: " + symbolTable.contains("y"));
        System.out.println("Symbol table contains identifier x: " + symbolTable.contains("x"));
        System.out.println("Identifier c was added to the Symbol table: " + check);
        System.out.println("Identifier c was added a second time to the Symbol table: " + check2);

    }
}