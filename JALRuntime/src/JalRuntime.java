import java.util.*;
import java.io.*;


public class JalRuntime {



    List<List<String>> allStatements = null;
    Stack<Integer> variableStack = new Stack<Integer>();
    HashMap<String, Stack<Integer>> SymbolTable = new HashMap<>();
    HashMap<String, Integer> functionStartLine = new HashMap<String, Integer>();

    /**
     *
     * @param args
     */
    public static void main(String[] args) throws IOException {

        String s = "test.jalclass";
        //new JalRuntime().evaluateBytecode(args[0]);
        new JalRuntime().evaluateBytecode(s);



    }


    private void evaluateBytecode(String filename) throws IOException {

        //System.out.println("In evaluateBytecode");
        parseFile(filename);


    }


    private void parseFile(String filename) throws IOException {

        //System.out.println("In parseFile");
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        int lineNum = 0;
        int startLine = 0;
        allStatements = new ArrayList<List<String>>();

        while ((line = br.readLine()) != null) {
            allStatements.add(new ArrayList<String>(Arrays.asList(line.split(" "))));
            //allCommands[length] = line.split(" ");
            if(allStatements.get(lineNum).get(0).equals(".start")){
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
        //System.out.println("parseFile allStatements:"+allStatements.get(0).get(2));
        int scanIndex = startLine;
        while (true) {
            if(allStatements.get(scanIndex).get(0).trim().equals(".end")  &&
                    allStatements.get(scanIndex).get(2).trim().equals("main")){
                break;
            }

            //excuteCommand(allCommands[scanIndex]);
            //System.out.println("allStatements.get(scanIndex):"+allStatements.get(scanIndex));
            excuteCommand(allStatements.get(scanIndex));
            scanIndex++;
        }
        br.close();
    }


    public void excuteCommand(List<String> command) {

            //System.out.println("In executeCommand: "+command);

            int a,b,result;
            switch(command.get(0)){
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
                    Stack<Integer> symbolTableStack = null;
                    if( SymbolTable.get(command.get(1)) == null ){
                         symbolTableStack = new Stack<Integer>();
                        symbolTableStack.push(variableStack.pop());

                    }
                    else{
                        symbolTableStack = SymbolTable.get(command.get(1));
                    }

                    SymbolTable.put(command.get(1), symbolTableStack);


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

    private void excuteFunction(List<String> command) {


            //System.out.println("In excuteFunction:"+command);

            String functionName = command.get(1).trim();
        //System.out.println("functionStartLine:"+functionName);
            int scanIndex = functionStartLine.get(functionName) + 1;
            //HashMap<String, Integer> LocalVariableCount = new HashMap<>();
            HashMap<String, Integer> localVariableMap = new HashMap<String,Integer>();
            while (true) {

                if(allStatements.get(scanIndex).get(0).trim().equals(".end")&&
                        allStatements.get(scanIndex).get(2).trim().equals(functionName)){
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
                excuteCommand(allStatements.get(scanIndex));
                scanIndex++;


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




