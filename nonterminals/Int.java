package nonterminals;

import tokenizer.Tokenizer;

public class Int {
    private int value;
    
    public void parseInt(Tokenizer tokens){
        // Check that token is an integer
        if (tokens.getToken() == 32){
            // Assign token value
            value = tokens.getTokenInteger();
            
            // Consume integer
            tokens.nextToken();
        }
    }
    
    public void printInt(){
        System.out.print(value);
    }
    
    public void execInt(){
        
    }
}
