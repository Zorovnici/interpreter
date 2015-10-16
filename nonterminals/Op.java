package nonterminals;

import tokenizer.Tokenizer;

public class Op {
    private Int integer;
    private Id id;
    private Exp exp;
    private String type;
    
    public void parseOp(Tokenizer tokens){
        if (tokens.getToken() == 31){ // The token is an integer
            // Parse <int>
            integer = new Int();
            integer.parseInt(tokens);
            
            // Set type to integer
            type = "integer";
        }
        else if (tokens.getToken() == 32){ // The token is an identifier
            // Parse <id>
            id = new Id();
            id.parseID(tokens);
            
            // Set type to id
            type = "id";
        }
        else if (tokens.getToken() == 20){ // The token is '('
            // Advance to next token
            tokens.nextToken();
            
            // Parse <exp>
            exp = new Exp();
            exp.parseExp(tokens);
            
            // Verify that next token is ')'
            if (tokens.getToken() != 21){
                System.out.println("Error: expected )");
                System.exit(0);
            }
            
            // Advance to next token
            tokens.nextToken();
            
            // Set type to exp
            type = "exp";
        }
        else {
            System.out.println("Error: expected <int>, <id>, or (<exp>)");
            System.exit(0);
        }
    }
    
    public void printOp(){
        switch (type){
            case "integer":
                // Print <int>
                integer.printInt();
                break;
            case "id":
                // Print <id>
                id.printId();
                break;
            case "exp":
                // Print (<exp>)
                System.out.println("(");
                exp.printExp();
                System.out.print(")");
        }
    }
    
    public void execOp(){
        
    }
}
