package nonterminals;

import tokenizer.Tokenizer;

public class DeclSeq {
    private Decl decl;
    private DeclSeq seq;
    
    public void parseDeclSeq(Tokenizer tokens){
        // Parse <decl>
        decl = new Decl();
        decl.parseDecl(tokens);
        
        // If the next token is int then this is a <decl seq>
        if (tokens.getToken() == 4){
            // Parse <decl seq>
            seq = new DeclSeq();
            seq.parseDeclSeq(tokens);
        }
    }
    
    public void printDeclSeq(){
        // Print <decl>
        decl.printDecl();
        
        // Print <decl seq>
        if(seq != null){
            seq.printDeclSeq();
        }
    }
    
    public void execDeclSeq(){
        
    }
}
