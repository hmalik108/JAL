import org.antlr.v4.runtime.Token;

/**
 * Created by Nikhil on 4/17/2016.
 */
public class FunctionNotDefined extends RuntimeException {

    private int line;
    private int column;
    private final String functionName;

    public FunctionNotDefined(Token functionNameToken){
        line = functionNameToken.getLine();
        column = functionNameToken.getCharPositionInLine();
        functionName = functionNameToken.getText();
    }

    @Override
    public String getMessage() {
        return line + ":" + column + " function not declared <" + functionName + ">";
    }
}
