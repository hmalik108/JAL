import org.antlr.v4.runtime.Token;

/**
 * Created by Nikhil on 4/17/2016.
 */
public class UndeclaredVariableException extends RuntimeException {
    private int line;
    private int column;
    private String varName;

    public UndeclaredVariableException(Token varNametoken){
        line = varNametoken.getLine();
        column = varNametoken.getCharPositionInLine();
        varName = varNametoken.getText();

    }
    @Override
    public String getMessage() {
        return line + ":" + column + " undeclared variable <" + varName + ">";
    }
}
