// Generated from C:/Users/Nikhil/Desktop/SER502 - Lang/JAL\JAL.g4 by ANTLR 4.5.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link JALParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface JALVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link JALParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(JALParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code MainStatement}
	 * labeled alternative in {@link JALParser#programPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainStatement(JALParser.MainStatementContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ProgPartFunctionDefinition}
	 * labeled alternative in {@link JALParser#programPart}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgPartFunctionDefinition(JALParser.ProgPartFunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(JALParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#branch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBranch(JALParser.BranchContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(JALParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#while_brach}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitWhile_brach(JALParser.While_brachContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Div}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDiv(JALParser.DivContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Relation}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelation(JALParser.RelationContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Variable}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariable(JALParser.VariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Or}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr(JALParser.OrContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Number}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(JALParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Mult}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMult(JALParser.MultContext ctx);
	/**
	 * Visit a parse tree produced by the {@code And}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd(JALParser.AndContext ctx);
	/**
	 * Visit a parse tree produced by the {@code String}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitString(JALParser.StringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Plus}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlus(JALParser.PlusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code Minus}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinus(JALParser.MinusContext ctx);
	/**
	 * Visit a parse tree produced by the {@code funcCallExpression}
	 * labeled alternative in {@link JALParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncCallExpression(JALParser.FuncCallExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#println}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintln(JALParser.PrintlnContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#print}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrint(JALParser.PrintContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#varDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDeclaration(JALParser.VarDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#assignment}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignment(JALParser.AssignmentContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#functionDefinition}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDefinition(JALParser.FunctionDefinitionContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#parameterDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParameterDeclaration(JALParser.ParameterDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#statementList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatementList(JALParser.StatementListContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(JALParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link JALParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(JALParser.ExpressionListContext ctx);
}