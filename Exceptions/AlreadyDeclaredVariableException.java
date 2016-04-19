import org.antlr.v4.runtime.Token;

/**
 * Created by Nikhil on 4/17/2016.
 */
public class AlreadyDeclaredVariableException extends RuntimeException {
    private int line;
    private int column;
    private String varName;

    public AlreadyDeclaredVariableException(Token varNametoken){
        line = varNametoken.getLine();
        column = varNametoken.getCharPositionInLine();
        varName = varNametoken.getText();

    }
    @Override
    public String getMessage() {
        return line + ":" + column + " Already declared variable <" + varName + ">";
    }
}
