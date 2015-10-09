import nonTerminals.*;
import tokenizer.*;

public class Interpreter {
    
    public static void main(String[] args) {
        // Get file name from command line argument
        String fileName = args[0];
        
        // Tokenize file
        Tokenizer tokens = new Tokenizer(fileName);
    }
    
}
