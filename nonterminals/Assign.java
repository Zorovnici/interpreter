package nonterminals;

import tokenizer.Tokenizer;

public class Assign {
    private ID id;
    private Exp exp;
    
    public void parseAssign(Tokenizer tokens){
        // Parse <id>
        id = new ID();
        id.parseID(tokens);
        
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
        id.printID();
        
        // Print '='
        System.out.print(" = ");
        
        // Print <exp>
        exp.printExp();
        
        // Print ;
        System.out.print(";");
    }
}
