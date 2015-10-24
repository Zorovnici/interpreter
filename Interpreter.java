import nonterminals.*;
import tokenizer.*;

public class Interpreter {
    
    public static void main(String[] args) {
        // Get program file name from command line argument
        String programFile = args[0];
        
        // Get input file name from command line argument
        String inputFile = args[1];
        
        // Tokenize file
        Tokenizer tokens = new Tokenizer(programFile);
        
        // Parse program
        Prog program = new Prog();
        program.parseProg(tokens);
        
        // Print program
        program.printProg();
    }
    
}
