import java.util.*;
import java.io.*;


public class JalRuntime {



    List<List<String>> allStatements = null;
    Stack<Integer> variableStack = new Stack<Integer>();
    HashMap<String, Stack<Integer>> SymbolTable = new HashMap<>();
    HashMap<String, Integer> functionStartLine = new HashMap<String, Integer>();

    private static int scanIndex = 0;
    private static boolean less_than_flag = false;
    private static boolean if_flag = false;
    private static int methodScanIndex = 0;

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String s = "while_sum_to_n.jalclass";//"test.jalclass";
        //new JalRuntime().evaluateBytecode(args[0]);
        new JalRuntime().evaluateBytecode(s);



    }


    private void evaluateBytecode(String filename) throws IOException {

        ////System.out.println("In evaluateBytecode");
        parseFile(filename);


    }


    private void parseFile(String filename) throws IOException {

        ////System.out.println("In parseFile");
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
            if(!allStatements.get(lineNum).isEmpty()  && allStatements.get(lineNum).get(0).equals(".start")){
                if(allStatements.get(lineNum).get(2).equals("main")){
                    startLine = lineNum;
                } else {
                    functionStartLine.put(allStatements.get(lineNum).get(2), lineNum);
                }
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
        scanIndex = startLine;
        while (true) {
            if(allStatements.get(scanIndex).get(0).trim().equals(".end")  &&
                    allStatements.get(scanIndex).get(2).trim().equals("main")){
                break;
            }

            //excuteCommand(allCommands[scanIndex]);
            //System.out.println("allStatements.get(scanIndex):"+allStatements.get(scanIndex));
            // //System.out.println("variable stack:"+variableStack);
            excuteCommand(allStatements.get(scanIndex));
            scanIndex++;
        }
        br.close();
    }


    public void excuteCommand(List<String> command) {

        ////System.out.println("In executeCommand: "+command);

        int a,b,result;
        ;//System.out.println("Switch command:"+command);
        switch(command.get(0)){




            case "branch_if:0":
                //System.out.println("In branch_if:0");
                evaluate_if(command);

                break;

            case "branch_loop:0":

                break;


            case "while:":

                evaluate_while(command);

                break;


            case "greater_than":

                //System.out.println(variableStack);
                b = variableStack.pop();
                a = variableStack.pop();

                if( a > b ){
                    if_flag = true;
                }
                else{
                    if_flag = false;
                }
                break;

            case "greater_than_or_equal":

                //System.out.println(variableStack);
                b = variableStack.pop();
                a = variableStack.pop();

                if( a >= b ){
                    if_flag = true;
                }
                else{
                    if_flag = false;
                }
                break;

            case "less_than":
                //System.out.println("In less_than");
                b = variableStack.pop();
                a = variableStack.pop();
                if( a < b){
                    less_than_flag = true;
                    if_flag = true;
                }
                else {
                    less_than_flag = false;
                    if_flag = false;
                }
                break;

            case "less_than_or_equal":
                //System.out.println("In less_than");
                b = variableStack.pop();
                a = variableStack.pop();
                if( a <= b){
                    less_than_flag = true;
                    if_flag = true;
                }
                else {
                    less_than_flag = false;
                    if_flag = false;
                }
                break;


            case "push":
                //variable.push(Integer.parseInt(command.get(1)));
                variableStack.push(Integer.parseInt(command.get(1)));
                break;
            case "add":
                b = variableStack.pop();
                a = variableStack.pop();
                result = b + a ;
                variableStack.push(result);
                break;
            case "sub":
                b = variableStack.pop();
                a = variableStack.pop();
                result = a - b ;
                variableStack.push(result);
                break;
            case "mul":
                b = variableStack.pop();
                a = variableStack.pop();
                result = b * a ;
                variableStack.push(result);
                break;
            case "div":


                b = variableStack.pop();
                a = variableStack.pop();

                if ( b == 0){
                    throw new ArithmeticException("Divide by zero not supported by JAL" );
                }
                else{
                    result = a / b ;
                    variableStack.push(result);
                }
                break;
            case "print":
                System.out.print(variableStack.peek());
                break;
            case "println":
                System.out.println(variableStack.peek());
                break;
            case "":
                break;
            case "store":
                // System.out.println("variable stack:"+variableStack);

                Stack<Integer> symbolTableStack = null;
                if( SymbolTable.get(command.get(1)) == null ){
                    symbolTableStack = new Stack<Integer>();
                    symbolTableStack.push(variableStack.pop());

                }
                else{
                    symbolTableStack = SymbolTable.get(command.get(1));
                    symbolTableStack.push(variableStack.pop());
                }

                SymbolTable.put(command.get(1), symbolTableStack);
                // System.out.println("SymbolTable :"+SymbolTable);
                // System.out.println("did u reach here");

                    /*
                    if (SymbolTable.get(command.get(1)) == null) {
                        Stack<Integer> variableStack = new Stack<>();
                        variableStack.push(variableStack.pop());
                        SymbolTable.put(command.get(1), variableStack);
                    } else {
                        SymbolTable.get(command.get(1)).push(variableStack.pop());
                    }
                    */
                break;
            case "load":
                //System.out.println("commsnd:"+command);
                //System.out.println("load:SymbolTable.get(command.get(1)).peek():"+SymbolTable.get(command.get(1)).peek());
                //System.out.println("load: variableStack"+variableStack);
                variableStack.push(SymbolTable.get(command.get(1)).peek());

                break;
            case ".start":
                //functionNames.put(command[2],new Pair())
                break;
            case ".end":
                //Function ends move back to global table
                //Function move back to local stack
                break;
            case "return":
                //Return top of the local stack
                break;
            case ".invoke":
                excuteFunction(command);
                break;

            default:
                //System.out.println();
                throw new IllegalArgumentException("Command not recognized" + command.get(0));
        }
    }

    private void evaluate_while(List<String> command) {

        int while_start_index = methodScanIndex;
        int while_end_index = 0;
        methodScanIndex++;
        while(!allStatements.get(methodScanIndex).contains("branch_loop:0"))
        {
            //System.out.println("In while 1st while, stats"+allStatements.get(methodScanIndex));
            excuteCommand(allStatements.get(methodScanIndex++));
        }


        if(if_flag){
            //System.out.println("In while if");
            methodScanIndex++;
            while(!allStatements.get(methodScanIndex).contains("end_while")){
                //System.out.println("In while if2"+allStatements.get(methodScanIndex));
                excuteCommand(allStatements.get(methodScanIndex));
                methodScanIndex++;
            }
            methodScanIndex = while_start_index;
            excuteCommand(allStatements.get(methodScanIndex));
            //while_end_index = methodScanIndex + 1;
        }

        else{
            //System.out.println("in while else");


            while(!allStatements.get(methodScanIndex).contains("end_while")){
                //System.out.println("stats:"+allStatements.get(methodScanIndex));
                methodScanIndex++;
                //excuteCommand(allStatements.get(methodScanIndex));
            }
            methodScanIndex++;
        }


    }

    private void evaluate_if(List<String> command) {


        //System.out.println("functionStartLine:"+command);
        // String functionName = command.get(1).trim();

        //System.out.println("evaluate_if if_flag"+allStatements.get(methodScanIndex));
        if(if_flag){
            methodScanIndex++;
            while(!allStatements.get(methodScanIndex).contains("goto") && !allStatements.get(methodScanIndex).contains("endIf")){
                excuteCommand(allStatements.get(methodScanIndex));
                methodScanIndex++;
            }
            methodScanIndex++;
            if(allStatements.get(methodScanIndex).contains("if_not_true:")){
                while(!allStatements.get(methodScanIndex).contains("endIf:")){
                    methodScanIndex++;
                }
            }

        }
        else{

            //System.out.println("else1"+allStatements.get(methodScanIndex));
            methodScanIndex++;
            while(!allStatements.get(methodScanIndex).contains("goto") && !allStatements.get(methodScanIndex).contains("endIf")){
                //System.out.println("else2"+allStatements.get(methodScanIndex));
                methodScanIndex++;
            }
            methodScanIndex++;
            if(allStatements.get(methodScanIndex).contains("if_not_true:")){
                methodScanIndex++;
                while(!allStatements.get(methodScanIndex).contains("endIf:")){
                    //System.out.println("else3"+allStatements.get(methodScanIndex));
                    excuteCommand(allStatements.get(methodScanIndex));
                    methodScanIndex++;
                }
            }


        }

        // //System.out.println("variable stack"+variableStack);
        //excuteCommand(allStatements.get(scanIndex));

    }

    private void excuteFunction(List<String> command) {


        //System.out.println("In excuteFunction:"+command);

        String functionName = command.get(1).trim();
        //System.out.println("functionStartLine:"+functionName);
        // int scanIndex = functionStartLine.get(functionName) + 1;
        methodScanIndex = functionStartLine.get(functionName) + 1;
        //HashMap<String, Integer> LocalVariableCount = new HashMap<>();
        HashMap<String, Integer> localVariableMap = new HashMap<String,Integer>();
        while (true) {

            if(!allStatements.get(methodScanIndex).isEmpty() && allStatements.get(methodScanIndex).get(0).trim().equals(".end")&&
                    allStatements.get(methodScanIndex).get(2).trim().equals(functionName)){
                break;
            }

                /*
                if(allStatements.get(scanIndex).get(0).equals("store")){

                   // ((localVariableMap.get(allStatements.get(scanIndex).get(1)) ==null ) ? localVariableMap.put(allStatements.get(scanIndex).get(1),1) :
                     localVariableMap.put(allStatements.get(scanIndex).get(1))
                }


                if (allCommands[scanIndex][0] == "store") {
                    if (LocalVariableCount.get(allCommands[scanIndex][1]) == null) {
                        LocalVariableCount.put(allCommands[scanIndex][1], 1);
                    } else {
                        int add1 = LocalVariableCount.get(allCommands[scanIndex][1]);
                        LocalVariableCount.put(allCommands[scanIndex][1], add1);
                    }
                }
                */
            excuteCommand(allStatements.get(methodScanIndex));
            methodScanIndex++;


        }
        /*
            for(Map.Entry<String, Integer> entry : LocalVariableCount.entrySet()){
                String LocalVariable = entry.getKey();
                int VariableCount = entry.getValue();
                while (VariableCount > 0) {
                    SymbolTable.get(LocalVariable).pop();
                }
            }
            */


    }


}







enum Tokens2     {

    IF_START("branch_if:0 if_true:") ,
    IF_END("branch_if:0 goto endIf"),
    ENDIF("branch_if:0 endIf:") ,
    IF_NOT_TRUE("branch_if:0 if_not_true:") ,
    WHILE_END("end_while");



    public final String pattern;

    private Tokens2(String pattern){
        this.pattern = pattern;
    }

    public String toString(){
        return this.pattern;
    }

}




