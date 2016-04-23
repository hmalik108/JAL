import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.io.*;


public class runtimeMain {
	Stack<Integer> variable = new Stack<>();
	HashMap<String, Stack<Integer>> SymbolTable = new HashMap<>();
	HashMap<String, Integer> FunctionStartLine = new HashMap<>();
    String[][] allCommands = null;

	public void excuteFuncion(String[] command) {
		String functionName = command[2];
		int scanIndex = FunctionStartLine.get(functionName) + 1;
		HashMap<String, Integer> LocalVariableCount = new HashMap<>();
		while (true) {
			if (allCommands[scanIndex][0] == ".end" &&
	        	allCommands[scanIndex][2] == functionName) {
	        	break;
	        }
			if (allCommands[scanIndex][0] == "store") {
				if (LocalVariableCount.get(allCommands[scanIndex][1]) == null) {
					LocalVariableCount.put(allCommands[scanIndex][1], 1);
				} else {
					int add1 = LocalVariableCount.get(allCommands[scanIndex][1]);
					LocalVariableCount.put(allCommands[scanIndex][1], add1);
				}
			}
			excuteCommand(allCommands[scanIndex]);
			scanIndex++;
		}
		for(Map.Entry<String, Integer> entry : LocalVariableCount.entrySet()){
			String LocalVariable = entry.getKey();
			int VariableCount = entry.getValue();
			while (VariableCount > 0) {
				SymbolTable.get(LocalVariable).pop();
			}
		}

	}
	
	
	public void excuteCommand(String[] command) {
		int a,b,result;
		switch(command[0]){
        case "push":
            variable.push(Integer.parseInt(command[1]));
            break;
        case "add":
            a = variable.pop();
            b = variable.pop();
            result = b + a ;
            variable.push(result);
            break;
        case "sub":
            a = variable.pop();
            b = variable.pop();
            result = b - a ;
            variable.push(result);
            break;
        case "mul":
            a = variable.pop();
            b = variable.pop();
            result = b * a ;
            variable.push(result);
            break;
        case "div":
            a = variable.pop();
            b = variable.pop();
            result = b / a ;
            variable.push(result);
            break;
        case "print":
            System.out.print(variable.peek());
            break;
        case "println":
            System.out.println(variable.peek());
            break;
        case "":
            break;
        case "store":
        	if (SymbolTable.get(command[1]) == null) {
        		Stack<Integer> variableStack = new Stack<>();
        		variableStack.push(variable.pop());
        		SymbolTable.put(command[1], variableStack);
        	} else {
        		SymbolTable.get(command[1]).push(variable.pop());
        	}
            break;
        case "load":
            variable.push(SymbolTable.get(command[1]).peek());
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
        	excuteFuncion(command);
            break;

        default:
            System.out.println();
            throw new IllegalArgumentException("Command not recognized" + command[0]);
		}
	}

	public void readFile(String fileName)throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;
        int length = 0;
        int startLine = 0;
        while ((line = br.readLine()) != null) {
        	allCommands[length] = line.split(" ");
        	if (allCommands[length][0] == ".start") {
        		if (allCommands[length][2] == "main") {
        			startLine = length;
        		} else {
        			FunctionStartLine.put(allCommands[length][2], length);
        		}
        	}
        	length++;
        }
        
        int scanIndex = startLine;
        while (true) {
        	if (allCommands[scanIndex][0] == ".end" &&
        		allCommands[scanIndex][2] == "main") {
        		break;
        	}
        	excuteCommand(allCommands[scanIndex]);
        }
        br.close();
	}
	
	
	public static void main(String[] args) throws IOException {
		test();
    }
	
	public static void test() {
		HashMap<String,Stack<Integer>> SymbolTable = new HashMap<>();

    	Stack<Integer> variableStack = new Stack<>();
    	variableStack.push(1);
    	if (SymbolTable.get("test1") == null) {
    		System.out.println(SymbolTable.get("test1"));
    	}
    	SymbolTable.put("test1", variableStack);
    	variableStack.push(2);
//    	SymbolTable.get("test1");
    	System.out.println(SymbolTable.get("test1"));
    	variableStack.push(3);
    	System.out.println(SymbolTable.get("test1"));
    	SymbolTable.get("test1").push(4);
    	System.out.println(SymbolTable.get("test1"));
    	
    	HashMap<String,Integer> test = new HashMap<>();
    	test.put("s1", 123);
    	int add1 = test.get("s1") + 1;
    	test.put("s1", add1);
    	System.out.println(test.get("s1"));
	}
}
