import java.util.Arrays;


/**
 *
 * JalStack : An array implementation of a stack
 *
 */
public class JalStack<Integer> {
    private final static int DEFAULT_CAPACITY = 100;

    private int top;
    private Integer[] stack;


    public JalStack()
    {
        this(DEFAULT_CAPACITY);
    }


    public JalStack(int initialCapacity)
    {
        top = 0;
        stack = (Integer[])(new Object[initialCapacity]);

    }


    public void push(Integer element)
    {
        if (size() == stack.length)
            expandCapacity();

        stack[top] = element;
        top++;
    }


    private void expandCapacity()
    {
        stack = Arrays.copyOf(stack, stack.length * 2);
    }


    public Integer pop() throws Exception
    {
        if (isEmpty())
            throw new Exception("Stack is Empty");

        top--;
        Integer result = stack[top];
        stack[top] = null;

        return result;
    }


    public Integer peek() throws Exception
    {
        if (isEmpty())
            throw new Exception("Stack is Empty");

        return stack[top-1];
    }


    public boolean isEmpty()
    {
        return (top == 0);
    }


    public int size()
    {
        return top;
    }


    public String toString()
    {
        String result = "";

        for (int scan=0; scan < top; scan++)
            result = result + stack[scan] + "\n";

        return result;
    }
}

