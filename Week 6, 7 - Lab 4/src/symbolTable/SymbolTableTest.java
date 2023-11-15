package symbolTable;

import org.junit.Assert;
import org.junit.Test;

public class SymbolTableTest {

    @Test
    public void testAdd() {
        SymbolTable symbolTable = new SymbolTable(3);

        boolean addedConstant = symbolTable.add("2");
        boolean addedIdentifier = symbolTable.add("c");
        boolean addedSameIdentifierAgain = symbolTable.add("c");

        Assert.assertTrue(addedConstant);
        Assert.assertTrue(addedIdentifier);
        Assert.assertFalse(addedSameIdentifierAgain);
    }

    @Test
    public void testAddAndContains() {
        SymbolTable symbolTable = new SymbolTable(4);

        symbolTable.add("c");
        symbolTable.add("y");
        symbolTable.add("2");
        symbolTable.add("1");

        Assert.assertTrue(symbolTable.contains("y"));
        Assert.assertTrue(symbolTable.contains("1"));
        Assert.assertFalse(symbolTable.contains("4"));
    }

    @Test
    public void testRemove() {
        SymbolTable symbolTable = new SymbolTable(4);

        symbolTable.add("c");
        symbolTable.add("y");
        symbolTable.add("2");
        symbolTable.add("1");

        Assert.assertTrue(symbolTable.contains("y"));
        Assert.assertTrue(symbolTable.contains("1"));

        symbolTable.remove("y");
        symbolTable.remove("1");

        Assert.assertFalse(symbolTable.contains("y"));
        Assert.assertFalse(symbolTable.contains("1"));
    }

}
