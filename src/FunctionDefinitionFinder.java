import org.antlr.v4.runtime.tree.ParseTree;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Nikhil on 4/17/2016.
 */
public class FunctionDefinitionFinder extends JALBaseVisitor<Set<String>> {

    public static Set<String> findFunctions(ParseTree tree){
        final Set<String> definedFunctions = new HashSet<>();
        new JALBaseVisitor<Void>(){
            @Override
            public Void visitFunctionDefinition(JALParser.FunctionDefinitionContext ctx) {
                String functionName = ctx.funcName.getText();
                if(definedFunctions.contains(functionName))
                    throw new FunctionAlreadyDefined(ctx.funcName);
                definedFunctions.add(functionName);
                return null;
            }
        }.visit(tree);
        return definedFunctions;
    }
}
