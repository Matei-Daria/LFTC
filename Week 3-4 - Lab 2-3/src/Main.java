import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Main {
    public static void main(String[] args) {

        System.out.println("p1");
        MyScanner scanner = new MyScanner("io/p1.txt");
        scanner.scan();
        try (PrintStream printStream = new PrintStream("io/p1-ST.out")) {
            printStream.println(scanner.getSymbolTable());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintStream printStream = new PrintStream("io/p1-PIF.out")) {
            printStream.println(scanner.getProgramInternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("p2");
        MyScanner scanner2 = new MyScanner("io/p2.txt");
        scanner2.scan();
        try (PrintStream printStream = new PrintStream("io/p2-ST.out")) {
            printStream.println(scanner.getSymbolTable());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintStream printStream = new PrintStream("io/p2-PIF.out")) {
            printStream.println(scanner.getProgramInternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("p3");
        MyScanner scanner3 = new MyScanner("io/p3.txt");
        scanner3.scan();
        try (PrintStream printStream = new PrintStream("io/p3-ST.out")) {
            printStream.println(scanner.getSymbolTable());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintStream printStream = new PrintStream("io/p3-PIF.out")) {
            printStream.println(scanner.getProgramInternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("p1err");
        MyScanner scanner1err = new MyScanner("io/p1err.txt");
        scanner1err.scan();
        try (PrintStream printStream = new PrintStream("io/p1err-ST.out")) {
            printStream.println(scanner.getSymbolTable());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (PrintStream printStream = new PrintStream("io/p1err-PIF.out")) {
            printStream.println(scanner.getProgramInternalForm());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}