import java.util.ArrayList;
import java.util.Arrays;

public class CharExtensions
{
    public static Boolean IsOperator(char c)
    {
        ArrayList<Character> operators = new ArrayList<Character>(Arrays.asList('+', '-', '*', '/', '=', '<', '>', '^', '|', '@', '$', '#', '&', '%'));
        return operators.contains(c);
    }
    public static Boolean IsSeparator(char c)
    {
        ArrayList<Character> separators = new ArrayList<Character>(Arrays.asList( '{', '}', ',', '(', ')', ';', ':', '.', '[', ']' ));
        return separators.contains(c);
    }
}
