grammar JAL;

program: programPart+ ;

 programPart: statement              #MainStatement
            | functionDefinition     #ProgPartFunctionDefinition
            ;

statement: println ';'
        | print ';'
        | varDeclaration ';'
        | assignment ';'
        | branch
        | while_brach
        ;

branch: 'if' '(' condition=expression ')' ':' onTrue=block 'else' ':' onFalse=block 'end_if'
        ;

block: statement* ;

while_brach: 'while' '(' condition=expression ')' ':' onCondTrue=block 'end_while' ;

expression: left=expression '/' right=expression #Div
        | left=expression '*' right=expression #Mult
        | left=expression '+' right=expression #Plus
        | left=expression '-' right=expression #Minus
        | left=expression operator=('<'|'>'|'<='|'>=') right=expression #Relation
        | left=expression '&&' right=expression #And
        | left=expression '||' right=expression #Or
        | number=NUMBER #Number
        | txt=STRING #String
        | varName=IDENTIFIER #Variable
        | functionCall #funcCallExpression
        ;

println: 'println(' argument=expression ')';

print: 'print(' argument=expression ')';

varDeclaration: 'int' varName=IDENTIFIER ;

assignment: varName=IDENTIFIER '=' expr=expression ;

functionDefinition: 'func int' funcName=IDENTIFIER '(' params=parameterDeclaration ')' ':' statements = statementList 'return' returnValue=expression ';' 'end_func' ;

parameterDeclaration: declarations+=varDeclaration (',' declarations+=varDeclaration)*
                    |
                    ;

statementList: statement* ;

functionCall: funcName=IDENTIFIER '(' arguments=expressionList ')';

expressionList: expressions+=expression (',' expressions+=expression)*
              |
              ;

IDENTIFIER: [a-zA-Z][a-zA-Z0-9]* ;
NUMBER: [0-9]+;
WHITESPACE: [ \t\n\r]+ -> skip;
STRING: '"' .*? '"' ;