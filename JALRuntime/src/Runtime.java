//import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Stack;

public class Runtime {


    public static Stack<Integer> variable;
    public static HashMap<String,LinkedList<Integer>> globalSymbolTable;
    public static BufferedReader br;
    public static FileInputStream fis;
    public static String line;

    private  static boolean WHILE_FLAG = false;
    private  static boolean WHILE_EXECUTION_FLAG = false;
    private static boolean IF_FLAG = false;


    private static void init(String filename){




    }

    public static void main(String[] args) throws IOException {


       // init(args[0]);

        String filename = "cond_stat2.jalclass";//args[0];

        // Scanner sc = new Scanner(System.in);

        //FileInputStream fis = new FileInputStream(filename);
        fis = new FileInputStream(filename);

        // BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        br = new BufferedReader(new InputStreamReader(fis));
        //String line = null;
        line = null;

        //Stack<Integer> variable = new Stack<>();
        variable = new Stack<Integer>();
        //HashMap<String,Integer> globalSymbolTable = new HashMap<>();
        globalSymbolTable = new HashMap<String, LinkedList<Integer>>();
//        HashMap<String,Pair> functionNames = new HashMap<>();

        executeFile();
    }

    private static void executeFile() throws IOException{

        int i = 0;
        while ((line = br.readLine()) != null) {
            ++i;
            line = line.trim();
            String[] command = line.split(" ");
            int a,b,result;
            switch(command[0])
            {

               // case checkBranch(command):

                case "while:":
                    WHILE_FLAG = true;
                    evaluate();




                case "greater_than":
                    int greater_than_v1 = variable.pop();
                    int greater_than_v2 = variable.pop();

                    if( greater_than_v2 > greater_than_v1 ){
                        IF_FLAG = true;
                        evaluate();
                    }
                    else{
                        IF_FLAG = false;
                        evaluate();
                    }

                    break;



                case "less_than":
                    int less_than_v1 = variable.pop();
                    int less_than_v2 = variable.pop();

                    if( less_than_v2 < less_than_v1 ){
                        IF_FLAG = true;
                        evaluate();
                    }
                    else{
                        IF_FLAG = false;
                        evaluate();
                    }

                    break;
                   // if()


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

                    LinkedList e = globalSymbolTable.get(command[1]);
                    if(e == null){
                        e = new LinkedList<Integer>();
                        e.add(variable.peek());
                    }
                    else{
                        e.add(variable.peek());
                    }


                    globalSymbolTable.put( command[1], e );
                  //  globalSymbolTable.put(command[1], (globalSymbolTable.get(command[1])==null)? (return new LinkedList<Integer>().add(2)): return new LinkedList<Integer>().add(2));
                    break;
                case "load":

                    variable.push(globalSymbolTable.get(command[1]).getLast());
                   // variable.push(globalSymbolTable.get(command[1]));
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

    private static void evaluate() throws IOException {

        StringBuilder while_block_sb = new StringBuilder();

        line = br.readLine();
        if( line == null || line.equals(""))
            evaluate();
        line = line.trim();

        if( IF_FLAG && line.trim().equals(Tokens1.IF_START.toString().trim()) ){
            //System.out.println(line);
            evaluate_if();
        }
        else if( !IF_FLAG && line.trim().equals(Tokens1.IF_START.toString().trim()) ){
            evaluate_else();
        }


        else if( WHILE_FLAG  ){


            //StringBuilder sb = new StringBuilder();

            while( line.trim().equals(Tokens1.WHILE_END.toString().toString())){

                while_block_sb.append(line);
                while_block_sb.append("\n");
                line = br.readLine();

            }
            System.out.println("while in sb :"+while_block_sb);
            evaluate_while(while_block_sb);


        }
    }

    private static void evaluate_while(StringBuilder sb) {

      //  if()


    }

    private static void evaluate_else() throws IOException{

        while(!(line.trim().equals(Tokens1.IF_NOT_TRUE.toString().trim()))){
            //System.out.println("Line here1:"+line);
            line = br.readLine();
        }

        line = br.readLine();
        //System.out.println("Line here2:"+line);

        while(!line.trim().equals(Tokens1.ENDIF.toString().trim())){

            if( line!= null && !line.equals("")){
                eval();
            }
            line = br.readLine();
           // System.out.println("Line here:"+line);
        }


    }

    private static void evaluate_if() throws IOException{

        line = br.readLine();
        while(!(line.trim()).equals(Tokens1.IF_END.toString().trim())){

            //System.out.println("Line here:"+line);
            //System.out.println("Line here:"+Tokens1.IF_END);
            if( line != null && !line.equals(""))
                eval();
            line = br.readLine();

        }

        while(!line.equals(Tokens1.ENDIF.toString())){

            line = br.readLine();
        }


    }



    private static void eval() throws IOException {

        line = line.trim();
        String[] command = line.split(" ");
        int a,b,result;
        switch(command[0])
        {

            // case checkBranch(command):




            case "greater_than":
                int greater_than_v1 = variable.pop();
                int greater_than_v2 = variable.pop();

                if( greater_than_v2 > greater_than_v1 ){
                    IF_FLAG = true;
                    evaluate();
                }
                else{
                    IF_FLAG = false;
                    evaluate();
                }

                break;



            case "less_than":
                int less_than_v1 = variable.pop();
                int less_than_v2 = variable.pop();

                if( less_than_v2 < less_than_v1 ){
                    IF_FLAG = true;
                    evaluate();
                }
                else{
                    IF_FLAG = false;
                    evaluate();
                }

                break;
            // if()




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

                LinkedList e = globalSymbolTable.get(command[1]);
                if(e == null){
                    e = new LinkedList<Integer>();
                    e.add(variable.peek());
                }
                else{
                    e.add(variable.peek());
                }


                globalSymbolTable.put( command[1], e );
                //  globalSymbolTable.put(command[1], (globalSymbolTable.get(command[1])==null)? (return new LinkedList<Integer>().add(2)): return new LinkedList<Integer>().add(2));
                break;
            case "load":

                variable.push(globalSymbolTable.get(command[1]).getLast());
                // variable.push(globalSymbolTable.get(command[1]));
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
}


enum Tokens1     {

    IF_START("branch_if:0 if_true:") ,
    IF_END("branch_if:0 goto endIf"),
    ENDIF("branch_if:0 endIf:") ,
    IF_NOT_TRUE("branch_if:0 if_not_true:") ,
    WHILE_END("end_while");



    public final String pattern;

    private Tokens1(String pattern){
        this.pattern = pattern;
    }

    public String toString(){
        return this.pattern;
    }

}