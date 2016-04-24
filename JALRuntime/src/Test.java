import com.sun.tools.doclets.formats.html.SourceToHTMLConverter;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by Harish Malik on 4/24/16.
 */
public class Test {


    public static void main(String[] args) {

        String[] arr  = new String[]{"a","s"};

        List<String> list = new ArrayList<String>(Arrays.<String>asList((arr)));

        System.out.println(list);

    }

}
