
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.util.*;

/**
 * Created by Nikhil on 4/15/2016.
 */
public class MyVisitor extends JALBaseVisitor<String> {

    private Map<String,Integer> variables = new HashMap<>();
    private final Set<String> definedFunctions;
    private int branchCounter = 0;
    private int compareCount = 0;
    private int addCounter = 0;
    private int orCounter = 0;
    private int whileCounter = 0;

    public MyVisitor(Set<String> definedFunctions) {
        if(definedFunctions == null)
            this.definedFunctions = Collections.emptySet();
        else
            this.definedFunctions = definedFunctions;
    }

    @Override
    public String visitPrintln(JALParser.PrintlnContext ctx) {
        return visit(ctx.argument) + "\n" + "println";
    }

    @Override
    public String visitPrint(JALParser.PrintContext ctx) {
        return visit(ctx.argument) + "\n" + "print";
    }

    @Override
    public String visitPlus(JALParser.PlusContext ctx) {
        return visitChildren(ctx) + "\n" + "add";
    }

    @Override
    public String visitMinus(JALParser.MinusContext ctx) {
        return visitChildren(ctx) + "\n" + "sub";
    }

    @Override
    public String visitMult(JALParser.MultContext ctx) {
        return visitChildren(ctx) + "\n" + "mul";
    }

    @Override
    public String visitDiv(JALParser.DivContext ctx) {
        return visitChildren(ctx) + "\n" + "div";
    }

    @Override
    public String visitNumber(JALParser.NumberContext ctx) {
        return "push " + ctx.number.getText();
    }

    @Override
    public String visitString(JALParser.StringContext ctx) {
        return "push " + ctx.txt.getText();
    }

    @Override
    public String visitVarDeclaration(JALParser.VarDeclarationContext ctx) {
        if(variables.containsKey(ctx.varName.getText()))
            throw new AlreadyDeclaredVariableException(ctx.varName);
        variables.put(ctx.varName.getText(),variables.size());
        return "";
    }

    @Override
    public String visitAssignment(JALParser.AssignmentContext ctx) {
        return visit(ctx.expr) + "\n" + "store " + ctx.varName.getText() + " loc:" + requireVariableToken(ctx.varName);
    }

    @Override
    public String visitVariable(JALParser.VariableContext ctx) {
        return "load "+ ctx.varName.getText() + " loc:" + requireVariableToken(ctx.varName);
    }

    private int requireVariableToken(Token varNameToken){
        Integer varIndex =  variables.get(varNameToken.getText());
        if(varIndex == null)
            throw new UndeclaredVariableException(varNameToken);
        return varIndex;
    }

    @Override
    public String visitFunctionCall(JALParser.FunctionCallContext ctx) {
        if(!definedFunctions.contains(ctx.funcName.getText())){
            throw new FunctionNotDefined(ctx.funcName);
        }
        String instructions = "";
        String argumentsInstructions = visit(ctx.arguments);
        if(argumentsInstructions != null){
            instructions += argumentsInstructions + "\n";
        }
        instructions+=".invoke " + ctx.funcName.getText() + " paramsCount: " +ctx.arguments.expressions.size();
        return instructions;
    }

    @Override
    public String visitFunctionDefinition(JALParser.FunctionDefinitionContext ctx) {
        Map<String,Integer> oldVariables = variables;
        variables = new HashMap<>();
        visit(ctx.params);
        String statementsInstructions = visit(ctx.statements);
        String result = ".start method " + ctx.funcName.getText() + " paramCount: " + ctx.params.declarations.size() + "\n" +
                (statementsInstructions == null ? "" : statementsInstructions + "\n") + visit(ctx.returnValue) + "\n" + "return" + "\n"
                + ".end method " + ctx.funcName.getText();
        variables = oldVariables;
        return result;
    }

    @Override
    public String visitProgram(JALParser.ProgramContext ctx) {
        String mainCode = "";
        String functions = "";

        for(int i=0;i<ctx.getChildCount();++i){
            ParseTree child = ctx.getChild(i);
            String instructions = visit(child);
            if(child instanceof JALParser.MainStatementContext){
                mainCode += instructions +"\n";
            }
            else{
                functions += instructions +"\n";
            }
        }
        return functions + "\n " + mainCode;
    }

    @Override
    public String visitBranch(JALParser.BranchContext ctx) {
        String conditionInstruction = visit(ctx.condition);
        String onTrueInstruction = visit(ctx.onTrue);
        String onFalseInstruction = visit(ctx.onFalse);
        int branchNum = branchCounter;
        ++branchCounter;

        return conditionInstruction + "\n" + "if_true : branch:" + branchNum + "\n" + onTrueInstruction + "\n" +
                "goto endIf : branch:" + branchNum + "\n" + "if_not_true  : branch:" + branchNum + "\n" + onFalseInstruction +"\n" +
                "endIf : branch:" + branchNum + "\n";
    }

    @Override
    public String visitWhile_brach(JALParser.While_brachContext ctx) {
        String conditionInstruction = visit(ctx.condition);
        String onTrueInstruction = visit(ctx.onCondTrue);
        int branchNum = whileCounter;
        ++whileCounter;

        return "while:" + "\n" + conditionInstruction + "\n" + "if_true : branch:" + branchNum + "\n" + onTrueInstruction
                + "\n" + "end_while" + "\n";
    }

    @Override
    public String visitAnd(JALParser.AndContext ctx) {
        String left = visit(ctx.left);
        String right = visit(ctx.right);
        int andNum = addCounter;
        ++addCounter;
        return left + "\n" + "ifeq onAndFalse" + andNum + "\n" + right + "\n" + "ifeq onAndFalse" + andNum + "\n"
                + "push 1\n" + "goto andEnd" + andNum + "\n" + "onAndFalse" +andNum + ":\n" + "push 0 :\n" +
                "andEnd" + andNum + ":";
    }

    @Override
    public String visitOr(JALParser.OrContext ctx) {
        String left = visit(ctx.left);
        String right = visit(ctx.right);
        int orNum = orCounter;
        ++orCounter;
        return left + "\n" + "ifne onOrTrue" + orNum + "\n" + right + "\n" + "ifne onOrTrue" + orNum + "\n"
                + "push 0\n" + "goto orEnd" + orNum + "\n" + "onOrTrue" + orNum + ":\n" + "push 1 :\n" +
                "orEnd" + orNum + ":";
    }

    @Override
    public String visitRelation(JALParser.RelationContext ctx) {
        String jumpInstruction;
        switch(ctx.operator.getText()){
            case "<" : jumpInstruction = "less_than"; break;
            case "<=" : jumpInstruction = "less_than_or_equal"; break;
            case ">" : jumpInstruction = "greater_than"; break;
            case ">=" : jumpInstruction = "greater_than_or_equal"; break;
            default: throw new IllegalArgumentException("Unknown Operator: " +ctx.operator.getText());
        }
        int compareNum = compareCount;
        ++compareCount;
        return visitChildren(ctx) + "\n" + jumpInstruction + "\n";
    }

    @Override
    protected String aggregateResult(String aggregate, String nextResult) {
        if(aggregate == null){
            return nextResult;
            }
        if(nextResult == null){
            return aggregate;
        }
        return aggregate + "\n" + nextResult;
    }
}
