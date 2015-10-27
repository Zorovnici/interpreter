import java.io.FileReader;
import java.util.Scanner;

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
        
        // Open input file
        Scanner in = null;
        try{
            in = new Scanner(new FileReader(inputFile));
        }catch(Exception e){
            System.out.print("Error: input file could not be opened");
            System.exit(0);
        }
        
        // Execute program
        program.executeProg(in);
    }
    
}
