import com.sun.media.sound.InvalidFormatException;
import org.antlr.v4.gui.TreeViewer;
import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * Created by Nikhil on 4/15/2016.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        if(args.length > 1 || args.length < 1)
            throw new IllegalArgumentException("Invalid Number of Arguments");
        if(!args[0].contains(".jal"))
            throw new InvalidFormatException("Invalid File Format");

        String filename = args[0];

        //Show Parse Tree
        //showGuiTreeView(filename);

        //Run the code to generate intermediate code
        runCode(filename);
    }

    public static void showGuiTreeView(String filename) throws Exception
    {

        final CharStream stream = new ANTLRFileStream(filename);
        final JALLexer lexer = new JALLexer(stream);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final JALParser parser = new JALParser(tokens);
        final ParseTree tree = parser.program();
        final List<String> ruleNames = Arrays.asList(JALParser.ruleNames);
        final TreeViewer view = new TreeViewer(ruleNames, tree);
        view.open();
    }

    public static void runCode(String filename) throws Exception {

        final CharStream stream = new ANTLRFileStream(filename);
        final JALLexer lexer = new JALLexer(stream);
        final CommonTokenStream tokens = new CommonTokenStream(lexer);
        final JALParser parser = new JALParser(tokens);
        final ParseTree tree = parser.program();
        Set<String> definedFunctions = FunctionDefinitionFinder.findFunctions(tree);
        createIntermediateFile(new MyVisitor(definedFunctions).visit(tree), filename);
    }


    public static void createIntermediateFile(String code, String filename) throws FileNotFoundException,
            UnsupportedEncodingException {
        String classname = filename.substring(0,filename.length()-4) + ".jalclass";
        PrintWriter writer = new PrintWriter(classname, "UTF-8");
        writer.println(code);
        writer.close();
    }

}
