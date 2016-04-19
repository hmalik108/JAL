grammar C;

declaration: typeSpecifier initDeclaratorList? ';' ;

typeSpecifier: ('bool' | 'int') ;

initDeclaratorList: initDeclarator
    | initDeclaratorList ',' initDeclarator ;

initDeclarator: declarator
    | declarator '=' (expression | Identifier) ;

declarator: Identifier | 'func' Identifier '(' parameterList ')' ;

Constant:[0-9]+ ;

Identifier: Nondigit(Nondigit | Digit)*;
fragment
Nondigit: [a-zA-Z_];
fragment
Digit: [0-9];


basicExpression
:   Constant
|   declarator
|   '(' expression ')'
;

multiplicativeExpression
:   basicExpression
|   multiplicativeExpression '*' multiplicativeExpression
|   multiplicativeExpression '/' multiplicativeExpression
|   multiplicativeExpression '%' multiplicativeExpression
;

additiveExpression
:   multiplicativeExpression
|   additiveExpression '+' multiplicativeExpression
|   additiveExpression '-' multiplicativeExpression
;

relationalExpression
:   additiveExpression
|   relationalExpression '<' additiveExpression
|   relationalExpression '>' additiveExpression
|   relationalExpression '<=' additiveExpression
|   relationalExpression '>=' additiveExpression
;

equalityExpression
:   relationalExpression
|   equalityExpression '==' relationalExpression
|   equalityExpression '!=' relationalExpression
;

logicalExpression
:   equalityExpression
|   logicalExpression ('||' | '&&') equalityExpression
;

assignmentExpression
:   logicalExpression
|   '=' assignmentExpression
;

expression
:   assignmentExpression
|   expression ',' assignmentExpression
;

expressionStatement:   expression? ';';

iterationStatement:
    'while' '(' expression ')' ':' statement* 'end_while' ;

selectionStatement:
    'if' '(' expression ')' ':' statement* ('else' ':' statement*)? 'end_if' ;

assignmentStatement: Identifier '=' expression ';' ;

returnStatement: 'return' expression ';' ;

statement: iterationStatement
    |selectionStatement
    |expressionStatement
    |assignmentStatement
    |returnStatement
    |declaration
    ;



parameterList: typeSpecifier? Identifier
    | parameterList ',' typeSpecifier? Identifier
    |
    ;

functionDefinition:
    typeSpecifier declarator ':' statement* 'end_func';


LineDirective
    :   '#' Whitespace? ~[\r\n]*
    -> skip
    ;

Whitespace
    :   [ \t]+
    -> skip
    ;

Newline
    :   (   '\r' '\n'?
    |   '\n'
    )
    -> skip
    ;

