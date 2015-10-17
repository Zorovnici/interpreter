package tokenizer;

public class Token {
    private Integer id;          // The numerical id of the token
    private Integer integer;     // The integer value of the token
    private String identifier;   // The identifier value of the token   

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getInteger() {
        return integer;
    }

    public void setInteger(int integer) {
        this.integer = integer;
    }
    
    public String getIdentifier(){
        return identifier;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }
}
