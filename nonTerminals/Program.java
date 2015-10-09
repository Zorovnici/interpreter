package nonTerminals;
import tokenizer.*;

public class Program {
    private DeclarationSequence decl; // Declaration Sequence
    private StatementSequence stmt;   // Statement Sequence
    
    public void parseProgram(Tokenizer tokens){
        // Verify that token is program
        if (tokens.getToken() != 1){
            System.out.println("Error: expected program");
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Create and parse declaration statement
        decl = new DeclarationSequence();
        decl.parseDeclarationSequence(tokens);
        
        // Verify that token is begin
        if (tokens.getToken() != 2){
            System.out.println("Error: expected begin");
        }
        
        // Create and parse statement sequence
        stmt = new StatementSequence();
        stmt.parseStatementSequence(tokens);
        
        // Advance to next token
        tokens.nextToken();
        
        // Verify that token is end
        if (tokens.getToken() != 2){
            System.out.println("Error: expected begin");
        }
    }
    
    public void printProgram(){
        System.out.println("program");
        printDeclarationSequence(decl);
        System.out.println("begin");
        printStatementSequence(stmt);
        System.out.println("end");
    }
    
    public void executeProgram() {
        
    }
}
