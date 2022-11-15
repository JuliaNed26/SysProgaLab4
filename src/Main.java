public class Main
{
    public static void main(String[] args)
    {
        Lexer lexer = new Lexer("C:\\Users\\nedav\\IdeaProjects\\Laba4\\in.txt");
        for(var token : lexer.Tokenize())
        {
            System.out.println(token);
        }
    }
}