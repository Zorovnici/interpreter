package nonterminals;

import java.util.HashSet;
import java.util.Set;
import tokenizer.Tokenizer;

public class Assign {
    private Id id;
    private Exp exp;
    
    // Stores identifiers to make sure that we don't recreate an identifier
    // that already exists.
    private static Set<String> identifiers = new HashSet<String>();
    
    public void parseAssign(Tokenizer tokens){
        // If the identifier doesn't already exist then create a new Id object
        if (!identifiers.contains(tokens.getTokenIdentifier())){
            // Parse <id>
            id = new Id();
            id.parseId(tokens);
            
            // Add identifier to identifiers set to avoid duplicates
            identifiers.add(tokens.getTokenIdentifier());
        }
        else{
            // Id exists so parse existing <id> object
            id.parseId(tokens);
        }
        
        // Verify that the next token is =
        if(tokens.getToken() != 14){
            System.out.println("Error: expected '='");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Parse <exp>
        exp = new Exp();
        exp.parseExp(tokens);
    }
    
    public void printAssign (){
        // Print <id>
        id.printId();
        
        // Print '='
        System.out.print(" = ");
        
        // Print <exp>
        exp.printExp();
        
        // Print ;
        System.out.print(";");
    }
}
