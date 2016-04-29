import java.util.*;
import java.io.*;
import java.util.concurrent.ExecutionException;


public class JalRuntime {


    List<List<String>> allStatements = null;
    Stack<Integer> variableStack = new Stack<Integer>();
    Stack<String> ifStack = new Stack<String>();
    Stack<String> whileStack = new Stack<String>();
    HashMap<String, Stack<Integer>> GlobalSymbolTable = new HashMap<>();
    HashMap<String, Integer> functionStartLine = new HashMap<String, Integer>();
    // HashMap<String, Stack<Integer>> globalStackDataTypeTable = new HashMap<String,Stack<Integer>>();
    HashMap<String, JalStack<Integer>> globalStackDataTypeTable = new HashMap<String, JalStack<Integer>>();
    private Stack<Integer> stack;
    private JalStack jalStack;

    private static int index = 0;
    private static boolean less_than_flag = false;
    private static boolean if_flag = false;
//    private static int methodScanIndex = 0;

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {

        //String s = "factorial_recursive.jalclass";//"test.jalclass";
        // String s = "if_else_find_maximum.jalclass";//"test.jalclass";
        String s = "stack.jalclass";//"test.jalclass";
        //System.out.println("hello");
        JalRuntime jrt = new JalRuntime();
        jrt.evaluateBytecode(s);
        //new JalRuntime().evaluateBytecode(s);
        //new JalRuntime().evaluateBytecode(args[0]);
        //System.out.println("JalStack stackL:\n" + jrt.jalStack);


    }

    private void evaluateBytecode(String filename) throws Exception {

        //////System.out.println("In evaluateBytecode");
        parseFile(filename);


    }

    private void parseFile(String filename) throws Exception {

        //////System.out.println("In parseFile");
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        int lineNum = 0;
        int startLine = 0;
        allStatements = new ArrayList<List<String>>();

        while ((line = br.readLine()) != null) {
            allStatements.add(new ArrayList<String>(Arrays.asList(line.split(" "))));
            //allCommands[length] = line.split(" ");
            ////System.out.println("allStatements"+allStatements);
            if (!allStatements.get(lineNum).isEmpty() && allStatements.get(lineNum).get(0).equals(".start")) {
                if (allStatements.get(lineNum).get(2).equals("main")) {
                    startLine = lineNum;
                }
                functionStartLine.put(allStatements.get(lineNum).get(2), lineNum);
            }
            /*
            if (allCommands[length][0] == ".start") {
                if (allCommands[length][2] == "main") {
                    startLine = length;
                } else {
                    FunctionStartLine.put(allCommands[length][2], length);
                }
            }
            */
            lineNum++;
        }
        ////System.out.println("parseFile allStatements:"+allStatements.get(0).get(2));
//        System.out.println(allStatements.get(scanIndex));
        index = startLine + 1;
        excuteFunction();
        br.close();
    }

    public void executeCommand(List<String> command, HashMap<String, Stack<Integer>> SymbolTable) throws Exception {

//    	int currentIndex = index; // just for test
//    	System.out.println("Index: " + currentIndex + " In executeCommand: " + command);
//    	System.out.println(variableStack);
//    	System.out.println("Index: " + index);
        int a, b, result;

        ;//System.out.println("Switch command:"+command);
        //System.out.println("command " + command);
        ////System.out.println("command "+command);
        switch (command.get(0)) {


            /*
            .start method main paramCount: 0
stack s

push_stack s 10
push_stack s 20
push_stack s 30
top_stack s
println
pop_stack s
top_stack s
println
pop_stack s
push 0
return
.end method main

             */


            case "stack":

                //String s = command.get(1);
                //stack = new Stack<Integer>();
                jalStack = new JalStack<Integer>();
                //globalStackDataTypeTable.put(command.get(1).trim(),stack);
                globalStackDataTypeTable.put(command.get(1).trim(), jalStack);
                index++;
                break;

            case "push_stack":

                //  //System.out.println("in push ");
                // //System.out.println("globalStackDataTypeTable "+globalStackDataTypeTable);
                // //System.out.println("string "+command.get(1));
                // //System.out.println("globalStackDataTypeTable  get"+globalStackDataTypeTable.get("s"));
                String stackName = command.get(1).trim();
                if (globalStackDataTypeTable.get(stackName) == null)
                    throw new Exception("Stack not initialized " + stackName);
                else {


                    //stack = globalStackDataTypeTable.get(stackName);
                    //stack.push(Integer.parseInt(command.get(2)));
                    jalStack = globalStackDataTypeTable.get(stackName);
                    jalStack.push(Integer.parseInt(command.get(2)));
                    //globalStackDataTypeTable.put(stackName, stack);
                    globalStackDataTypeTable.put(stackName, jalStack);
                    //System.out.println("globalStackDataTypeTable "+globalStackDataTypeTable);
                }

                index++;
                break;

            case "top_stack":

                //System.out.println("In top");
                //System.out.println("globalStackDataTypeTable "+globalStackDataTypeTable);
                if (globalStackDataTypeTable.get(command.get(1)) == null)
                    throw new Exception("Stack not found " + command.get(1));
                else {

                    // stack = globalStackDataTypeTable.get(command.get(1));
                    jalStack = globalStackDataTypeTable.get(command.get(1));
                    //variableStack.push(stack.peek());
                    // Integer i = (Integer) jalStack.peek();
                    variableStack.push((Integer) jalStack.peek());


                }
                index++;
                break;

            case "pop_stack":


                if (globalStackDataTypeTable.get(command.get(1)) == null)
                    throw new Exception("Stack not found " + command.get(1));
                else {
                    //stack = globalStackDataTypeTable.get(command.get(1));
                    jalStack = globalStackDataTypeTable.get(command.get(1));
                    //stack.pop();
                    jalStack.pop();
                    globalStackDataTypeTable.put(command.get(1), jalStack);


                }
                index++;
                break;

            case "branch_if:":
                //System.out.println("In branch_if:0");
                ifStack.push(command.get(3));
                evaluate_if(SymbolTable);
                index++;
                break;

            case "branch_loop:":
                index++;
                break;


            case "branch_if:0":
                ////System.out.println("In branch_if:0");
                evaluate_if(SymbolTable);
                index++;
                break;


            case "branch_loop:0":
                index++;
                break;


            case "while:":

                if (!whileStack.contains(command.get(1))) {
                    whileStack.push(command.get(1));
                }
                evaluate_while(SymbolTable);

                break;


            case "greater_than":

                ////System.out.println(variableStack);
                b = variableStack.pop();
                a = variableStack.pop();

                if (a > b) {
//                    if_flag = true;
                    variableStack.push(1);
                } else {
//                    if_flag = false;
                    variableStack.push(0);
                }
                index++;
                break;

            case "greater_than_or_equal":

                ////System.out.println(variableStack);
                b = variableStack.pop();
                a = variableStack.pop();

                if (a >= b) {
//                    if_flag = true;
                    variableStack.push(1);
                } else {
//                    if_flag = false;
                    variableStack.push(0);
                }
                index++;

                break;

            case "less_than":
                ////System.out.println("In less_than");
                b = variableStack.pop();
                a = variableStack.pop();
                if (a < b) {
//                    if_flag = true;
                    variableStack.push(1);
                } else {
//                    if_flag = false;
                    variableStack.push(0);
                }
                index++;

                break;

            case "less_than_or_equal":
                ////System.out.println("In less_than");
                b = variableStack.pop();
                a = variableStack.pop();
                if (a <= b) {
//                    if_flag = true;
                    variableStack.push(1);
                } else {
//                    if_flag = false;
                    variableStack.push(0);
                }
                index++;

                break;


            case "push":
                if (command.get(1).contains("true")) {
                    variableStack.push(1);
                } else if (command.get(1).contains("false")) {
                    variableStack.push(0);
                } else {
                    variableStack.push(Integer.parseInt(command.get(1)));
                }
//                System.out.println(variableStack);
                index++;

                break;
            case "add":
                b = variableStack.pop();
                a = variableStack.pop();
                result = b + a;
                variableStack.push(result);
                index++;

                break;
            case "sub":
                b = variableStack.pop();
                a = variableStack.pop();
                result = a - b;
                variableStack.push(result);
                index++;

                break;
            case "mul":
                b = variableStack.pop();
                a = variableStack.pop();
                result = b * a;
                variableStack.push(result);
                index++;

                break;
            case "div":
                b = variableStack.pop();
                a = variableStack.pop();

                if (b == 0) {
                    throw new ArithmeticException("Divide by zero not supported by JAL");
                } else {
                    result = a / b;
                    variableStack.push(result);
                }
                index++;

                break;
            case "print":
                System.out.print(variableStack.peek());
                index++;

                break;
            case "println":
                System.out.println(variableStack.peek());
                index++;

                break;
            case "":
                index++;

                break;
            case "store":
                // //System.out.println("variable stack:"+variableStack);
//            	//System.out.println("before store" + variableStack);
                Stack<Integer> symbolTableStack = null;
                if (SymbolTable.get(command.get(1)) == null) {
                    symbolTableStack = new Stack<Integer>();
                    symbolTableStack.push(variableStack.pop());

                } else {
                    symbolTableStack = SymbolTable.get(command.get(1));
                    symbolTableStack.push(variableStack.pop());
                }
//                //System.out.println("after store" + variableStack);
                SymbolTable.put(command.get(1), symbolTableStack);
                index++;

                break;
            case "load":
//                variableStack.push(SymbolTable.get(command.get(1)).peek());

                if (SymbolTable.get(command.get(1)) != null) {
                    variableStack.push(SymbolTable.get(command.get(1)).peek());
                } else {
                    if (GlobalSymbolTable.get(command.get(1)) != null) {
                        variableStack.push(GlobalSymbolTable.get(command.get(1)).peek());
                    } else {
                        // error
                    }
                }
                index++;

                break;
            case ".start":
                //functionNames.put(command[2],new Pair())
                index++;

                break;
            case ".end":
                index++;

                //Function ends move back to global table
                //Function move back to local stack
                break;
            case "return":
                index++;

                //Return top of the local stack
                break;
            case ".invoke":
                String functionName = command.get(1);
                int functionReturnLine = index;
                index = functionStartLine.get(functionName) + 1;
                excuteFunction();
                index = functionReturnLine + 1;
                break;

            default:
                ////System.out.println();
                throw new IllegalArgumentException("Command not recognized" + command.get(0));
        }
//        //System.out.println("Index: " + currentIndex + " after executeCommand: " + command);
//        //System.out.println(variableStack);


    }

    private void evaluate_while(HashMap<String, Stack<Integer>> SymbolTable) throws Exception {


        int while_start_index = index;
        int while_end_index = 0;
        index++;
        while(!(allStatements.get(index).contains("branch_loop:") &&
                allStatements.get(index).contains(whileStack.peek())) )
        {
            //System.out.println("In while 1st while, stats"+allStatements.get(methodScanIndex));
            // count local variable

            executeCommand(allStatements.get(index), SymbolTable);
        }

        int condition_value = variableStack.pop();
        if (condition_value == 1) {
            if_flag = true;
        } else {
            if_flag = false;
        }

        if(if_flag){
            //System.out.println("In while if");
//        	index++;
            while(!(allStatements.get(index).contains("end_while:") &&
                    allStatements.get(index).contains(whileStack.peek())) ){
                //System.out.println("In while if2"+allStatements.get(methodScanIndex));
                // count local variable

                executeCommand(allStatements.get(index), SymbolTable);

            }
            index = while_start_index;
            // count local variable

            executeCommand(allStatements.get(index), SymbolTable);
            //while_end_index = methodScanIndex + 1;
        }

        else{
            //System.out.println("in while else");


            while(!(allStatements.get(index).contains("end_while:") &&
                    allStatements.get(index).contains(whileStack.peek())) ){
                //System.out.println("stats:"+allStatements.get(methodScanIndex));
                index++;
                //excuteCommand(allStatements.get(methodScanIndex));
            }
            index++;
            whileStack.pop();
        }

    }


    
    private void evaluate_if(HashMap<String, Stack<Integer>> SymbolTable) throws Exception {

        //System.out.println("functionStartLine:"+command);
        // String functionName = command.get(1).trim();
        int condition_value = variableStack.pop();
        if (condition_value == 1) {
            if_flag = true;
        } else {
            if_flag = false;
        }
        //System.out.println("evaluate_if if_flag"+allStatements.get(methodScanIndex));
        index++;
        if(if_flag){
            while(!(allStatements.get(index).contains("goto") &&
                    allStatements.get(index).contains("endIf:") &&
                    allStatements.get(index).contains(ifStack.peek())) ){

                executeCommand(allStatements.get(index), SymbolTable);
//            	index++;
            }
            index++;
            if(allStatements.get(index).contains("if_not_true:")){

                while(!(allStatements.get(index).contains("endIf:") &&
                        allStatements.get(index).contains(ifStack.peek()) )){
                    index++;
                }
            } else {
                index--;
            }
//            index++;
        }
        else{

            //System.out.println("else1"+allStatements.get(methodScanIndex));
            while(!(allStatements.get(index).contains("goto") &&
                    allStatements.get(index).contains("endIf:") &&
                    allStatements.get(index).contains(ifStack.peek())) ){
                //System.out.println("else2"+allStatements.get(methodScanIndex));
                index++;
            }
            index++;
            if(allStatements.get(index).contains("if_not_true:")){
                index++;
                while(!(allStatements.get(index).contains("endIf:") &&
                        allStatements.get(index).contains(ifStack.peek())) ){
                    executeCommand(allStatements.get(index), SymbolTable);
//                    index++;
                }
//                index++;
            }
        }
        ifStack.pop();
    }

    
    private void excuteFunction() throws Exception {

        HashMap<String, Stack<Integer>> localSymbolTable = new HashMap<String, Stack<Integer>>();
        while (true) {

            if(!allStatements.get(index).isEmpty() && 
            		allStatements.get(index).get(0).trim().equals(".end")){
                break;
            }
            executeCommand(allStatements.get(index), localSymbolTable);
        }
    }
    
}



enum Tokens     {

    GO_TO("goto"),
    END_IF("endIf"),
    IF_NOT_TRUE("if_not_true:");


   /* IF_START("branch_if:0 if_true:") ,
    IF_END("branch_if:0 goto endIf"),
   // ENDIF("branch_if:0 endIf:") ,
    IF_NOT_TRUE("branch_if:0 if_not_true:") ,
    WHILE_END("end_while");

    */





    public final String pattern;

    private Tokens(String pattern){
        this.pattern = pattern;
    }

    public String toString(){
        return this.pattern;
    }

}




