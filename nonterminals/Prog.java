package nonterminals;
import tokenizer.*;

public class Prog {
    private DeclSeq declSeq;   // Declaration Sequence
    private StmtSeq stmtSeq;   // Statement Sequence
    
    public void parseProg(Tokenizer tokens){
        // Verify that token is program
        if (tokens.getToken() != 1){
            System.out.println("Error: expected program");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Create and parse <decl seq>
        declSeq = new DeclSeq();
        declSeq.parseDeclSeq(tokens);
        
        // Verify that token is begin
        if (tokens.getToken() != 2){
            System.out.println("Error: expected begin");
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
                
        // Create and parse <stmt seq>
        stmtSeq = new StmtSeq();
        stmtSeq.parseStmtSeq(tokens);

        // Verify that token is end
        if (tokens.getToken() != 3){
            System.out.println("Error: expected end, token: " + tokens.getToken());
            System.exit(0);
        }
        
        // Advance to next token
        tokens.nextToken();
        
        // Verify that the next token is EOF
        if (tokens.getToken() != 33){
            System.out.println("Error: expected EOF");
            System.exit(0);
        }
        
        // Verify that there are no more tokens after EOF
        if (!tokens.isEmpty()){
            System.out.println("Error: there are tokens after EOF");
            System.exit(0);
        }
    }
    
    public void printProg(){
        // Print program
        System.out.print("program");
        
        // Print <decl seq>
        declSeq.printDeclSeq(1);
        
        // Print begin
        System.out.println();
        System.out.print("   begin");
        
        // Print <stmt seq>
        stmtSeq.printStmtSeq(2);
        System.out.println();
        System.out.print("   end");
    }
    
    public void executeProg() {
        
    }
}
