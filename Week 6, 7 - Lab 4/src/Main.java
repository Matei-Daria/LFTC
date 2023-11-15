import finiteAutomata.FiniteAutomata;
import scanner.MyScanner;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    private static void scanner() {
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


    private static void printFiniteAutomataMenu() {
        System.out.println("Get the following information about the FA:");
        System.out.println("1. States");
        System.out.println("2. Alphabet");
        System.out.println("3. Transitions");
        System.out.println("4. Initial state");
        System.out.println("5. Final states");
        System.out.println("6. Verify if it is DFA");
        System.out.println("7. Enter a sequence and see if it's valid");
        System.out.println("8. Close the application");
        System.out.println("Enter an option: ");
    }

    private static void finiteAutomata() {
        FiniteAutomata finiteAutomata = new FiniteAutomata("io/FA.in");

        printFiniteAutomataMenu();
        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        while (option != 8) {
            switch (option) {
                case 1:
                    System.out.println("States: ");
                    System.out.println(finiteAutomata.getStates());
                    System.out.println();
                    break;

                case 2:
                    System.out.println("Alphabet: ");
                    System.out.println(finiteAutomata.getAlphabet());
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Transitions: ");
                    System.out.println(finiteAutomata.getTransitionFunctionsToString());
                    break;

                case 4:
                    System.out.println("Initial state: ");
                    System.out.println(finiteAutomata.getInitialState());
                    System.out.println();
                    break;


                case 5:
                    System.out.println("Final states: ");
                    System.out.println(finiteAutomata.getFinalStates());
                    System.out.println();
                    break;

                case 6:
                    System.out.println(finiteAutomata.isDFA() ? "It is DFA." : "It is not DFA.");
                    break;

                case 7:
                    System.out.println("Enter a sequence: ");
                    Scanner scanner2 = new Scanner(System.in);
                    String sequence = scanner2.nextLine();

                    if (finiteAutomata.verifySequence(sequence))
                        System.out.println("Sequence is valid.");
                    else
                        System.out.println("Sequence is not valid.");
                    break;

                default:
                    System.out.println("Invalid command!");
                    break;
            }
            System.out.println();
            printFiniteAutomataMenu();
            option = scanner.nextInt();
        }
    }

    public static void main(String[] args) {
        System.out.println("1. Scanner");
        System.out.println("2. Finite Automata");
        System.out.println("Enter an option: ");

        Scanner scanner = new Scanner(System.in);
        int option = scanner.nextInt();

        switch (option) {
            case 1:
                scanner();
                break;
            case 2:
                finiteAutomata();
                break;

            default:
                System.out.println("Not a valid option.");
                break;

        }

    }

}