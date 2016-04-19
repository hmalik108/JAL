import jdk.internal.util.xml.impl.Pair;

import java.io.*;
import java.util.HashMap;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        String filename = args[0];
        FileInputStream fis = new FileInputStream(filename);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String line = null;

        Stack<Integer> variable = new Stack<>();
        HashMap<String,Integer> globalSymbolTable = new HashMap<>();
        HashMap<String,Pair> functionNames = new HashMap<>();

        int i = 0;
        while ((line = br.readLine()) != null) {
            ++i;
            line = line.trim();
            String[] command = line.split(" ");
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
                    globalSymbolTable.put(command[1],variable.pop());
                    break;
                case "load":
                    variable.push(globalSymbolTable.get(command[1]));
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
                    break;

                default:
                    System.out.println();
                    throw new IllegalArgumentException("Command not recognized" + command[0]);
            }
        }

        br.close();
    }
}
