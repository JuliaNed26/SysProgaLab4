import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Lexer
{
    String filePath;


    public Lexer(String path)
    {
        filePath = path;
    }

    public List<Token> Tokenize()
    {
        List<Token> tokens = new ArrayList<Token>();

        try
        {
            File fileToRead = new File(this.filePath);
            InputStream in = new FileInputStream(fileToRead);
            Reader reader = new InputStreamReader(in);
            PushbackReader pushBackReader = new PushbackReader(reader);

            int symbolNum;
            try
            {
                while((symbolNum = pushBackReader.read()) != -1)
                {
                    char curSymb = (char)symbolNum;
                    if(Character.isWhitespace(curSymb))
                    {
                        continue;
                    }
                    else if (Character.isLetter(curSymb))
                    {
                        String readChunk = CodeReader.ReadWord(curSymb, pushBackReader);

                        if (CodeReader.IsReservedWord(readChunk))
                        {
                            tokens.add(new Token(TokenType.ReservedWord, readChunk));
                        }
                        else
                        {
                            tokens.add((new Token(TokenType.Identificator, readChunk)));
                        }
                    }
                    else if (Character.isDigit(curSymb))
                    {
                        tokens.add(new Token(TokenType.Number, CodeReader.ReadDigit(curSymb, pushBackReader)));
                    }
                    else if (curSymb == '\'')
                    {
                        tokens.add(new Token(TokenType.StringOrCharacter, CodeReader.ReadStringOrCharacter(curSymb, pushBackReader)));
                    }
                    else if (curSymb == '{')
                    {
                        int symbReadNum = pushBackReader.read();
                        if (symbReadNum != -1 && (char)symbReadNum == '$')
                        {
                            tokens.add(new Token(TokenType.CompilerDirective, CodeReader.ReadCompilerDirective(curSymb, pushBackReader)));
                        }
                        else
                        {
                            pushBackReader.unread(symbReadNum);
                            tokens.add(new Token(TokenType.Separator, Character.toString(curSymb)));
                        }
                    }
                    else if (curSymb == '(')
                    {
                        int symbReadNum = pushBackReader.read();
                        if (symbReadNum != -1 && (char)symbReadNum == '*')
                        {
                            CodeReader.ReadComment(curSymb, pushBackReader);
                        }
                        else
                        {
                            pushBackReader.unread(symbReadNum);
                            tokens.add(new Token(TokenType.Separator, Character.toString(curSymb)));
                        }
                    }
                    else if (curSymb == ':')
                    {
                        int symbReadNum = pushBackReader.read();
                        if (symbReadNum != -1 && (char)symbReadNum == '=')
                        {
                            tokens.add(new Token(TokenType.Operator, ":=" ));
                        }
                        else
                        {
                            pushBackReader.unread(symbReadNum);
                            tokens.add(new Token(TokenType.Separator, Character.toString(curSymb)));
                        }
                    }
                    else if (CharExtensions.IsOperator(curSymb))
                    {
                        tokens.add(new Token(TokenType.Operator, Character.toString(curSymb)));
                    }
                    else if (CharExtensions.IsSeparator(curSymb))
                    {
                        tokens.add(new Token(TokenType.Separator, Character.toString(curSymb)));
                    }
                    else
                    {
                        tokens.add(new Token(TokenType.Invalid, Character.toString(curSymb)));
                    }
                }
            }
            catch (IOException var10)
            {
                System.out.println("An error occurred.");
                var10.printStackTrace();
            }
        }
        catch (FileNotFoundException var11)
        {
            System.out.println("An error occurred.");
            var11.printStackTrace();
        }

        return tokens;
    }
}
