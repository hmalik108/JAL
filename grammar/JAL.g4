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
        | stack
        | stackOperations ';'
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
        | 'true' #true
        | 'false' #false
        | txt=STRING #String
        | varName=IDENTIFIER #Variable
        | functionCall #funcCallExpression
        | stackOperations #stackops
        ;

println: 'println(' argument=expression ')';

print: 'print(' argument=expression ')';

varDeclaration: type=typeSpecifier varName=IDENTIFIER ;

typeSpecifier: 'int' | 'bool' ;

assignment: varName=IDENTIFIER '=' expr=expression ;

functionDefinition: 'func' typeSpecifier funcName=IDENTIFIER '(' params=parameterDeclaration ')' ':' statements = statementList 'return' returnValue=expression ';' 'end_func' ;

parameterDeclaration: declarations+=varDeclaration (',' declarations+=varDeclaration)*
                    |
                    ;

stack: 'stack' varName=IDENTIFIER ';' ;

stackOperations: varName=IDENTIFIER '.' 'push(' num=NUMBER ')' |
                 varName=IDENTIFIER '.' 'pop()' |
                 varName=IDENTIFIER '.' 'top()' |
                 varName=IDENTIFIER '.' 'isEmpty()'
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
BOOLEAN: 'true'
        | 'false'
        ;