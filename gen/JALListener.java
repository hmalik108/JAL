// Generated from C:/Users/Nikhil/Desktop/SER502 - Lang/JAL\JAL.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link JALParser}.
 */
public interface JALListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link JALParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(JALParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(JALParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code MainStatement}
	 * labeled alternative in {@link JALParser#programPart}.
	 * @param ctx the parse tree
	 */
	void enterMainStatement(JALParser.MainStatementContext ctx);
	/**
	 * Exit a parse tree produced by the {@code MainStatement}
	 * labeled alternative in {@link JALParser#programPart}.
	 * @param ctx the parse tree
	 */
	void exitMainStatement(JALParser.MainStatementContext ctx);
	/**
	 * Enter a parse tree produced by the {@code ProgPartFunctionDefinition}
	 * labeled alternative in {@link JALParser#programPart}.
	 * @param ctx the parse tree
	 */
	void enterProgPartFunctionDefinition(JALParser.ProgPartFunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code ProgPartFunctionDefinition}
	 * labeled alternative in {@link JALParser#programPart}.
	 * @param ctx the parse tree
	 */
	void exitProgPartFunctionDefinition(JALParser.ProgPartFunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(JALParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(JALParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#branch}.
	 * @param ctx the parse tree
	 */
	void enterBranch(JALParser.BranchContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#branch}.
	 * @param ctx the parse tree
	 */
	void exitBranch(JALParser.BranchContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(JALParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(JALParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#while_brach}.
	 * @param ctx the parse tree
	 */
	void enterWhile_brach(JALParser.While_brachContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#while_brach}.
	 * @param ctx the parse tree
	 */
	void exitWhile_brach(JALParser.While_brachContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Div}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterDiv(JALParser.DivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitDiv(JALParser.DivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterRelation(JALParser.RelationContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitRelation(JALParser.RelationContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterVariable(JALParser.VariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitVariable(JALParser.VariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Or}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterOr(JALParser.OrContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitOr(JALParser.OrContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Number}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterNumber(JALParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitNumber(JALParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMult(JALParser.MultContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMult(JALParser.MultContext ctx);
	/**
	 * Enter a parse tree produced by the {@code And}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterAnd(JALParser.AndContext ctx);
	/**
	 * Exit a parse tree produced by the {@code And}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitAnd(JALParser.AndContext ctx);
	/**
	 * Enter a parse tree produced by the {@code String}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterString(JALParser.StringContext ctx);
	/**
	 * Exit a parse tree produced by the {@code String}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitString(JALParser.StringContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterPlus(JALParser.PlusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitPlus(JALParser.PlusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterMinus(JALParser.MinusContext ctx);
	/**
	 * Exit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitMinus(JALParser.MinusContext ctx);
	/**
	 * Enter a parse tree produced by the {@code funcCallExpression}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void enterFuncCallExpression(JALParser.FuncCallExpressionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code funcCallExpression}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 */
	void exitFuncCallExpression(JALParser.FuncCallExpressionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#println}.
	 * @param ctx the parse tree
	 */
	void enterPrintln(JALParser.PrintlnContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#println}.
	 * @param ctx the parse tree
	 */
	void exitPrintln(JALParser.PrintlnContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#print}.
	 * @param ctx the parse tree
	 */
	void enterPrint(JALParser.PrintContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#print}.
	 * @param ctx the parse tree
	 */
	void exitPrint(JALParser.PrintContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterVarDeclaration(JALParser.VarDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#varDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitVarDeclaration(JALParser.VarDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#assignment}.
	 * @param ctx the parse tree
	 */
	void enterAssignment(JALParser.AssignmentContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#assignment}.
	 * @param ctx the parse tree
	 */
	void exitAssignment(JALParser.AssignmentContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void enterFunctionDefinition(JALParser.FunctionDefinitionContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#functionDefinition}.
	 * @param ctx the parse tree
	 */
	void exitFunctionDefinition(JALParser.FunctionDefinitionContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void enterParameterDeclaration(JALParser.ParameterDeclarationContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 */
	void exitParameterDeclaration(JALParser.ParameterDeclarationContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#statementList}.
	 * @param ctx the parse tree
	 */
	void enterStatementList(JALParser.StatementListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#statementList}.
	 * @param ctx the parse tree
	 */
	void exitStatementList(JALParser.StatementListContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void enterFunctionCall(JALParser.FunctionCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#functionCall}.
	 * @param ctx the parse tree
	 */
	void exitFunctionCall(JALParser.FunctionCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link JALParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void enterExpressionList(JALParser.ExpressionListContext ctx);
	/**
	 * Exit a parse tree produced by {@link JALParser#expressionList}.
	 * @param ctx the parse tree
	 */
	void exitExpressionList(JALParser.ExpressionListContext ctx);
}