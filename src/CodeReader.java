import java.io.IOException;
import java.io.PushbackReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeReader
{
    public static String ReadWord(char curSymb, PushbackReader reader)
    {
        StringBuilder readWordBuilder = new StringBuilder(Character.toString(curSymb));
        try
        {
            int symbolNum = reader.read();
            while (symbolNum != -1 && (Character.isLetterOrDigit((char)symbolNum) || (char)symbolNum == '_'))
            {
                readWordBuilder.append((char)symbolNum);
                symbolNum = reader.read();
            }
            if(symbolNum != -1)
                reader.unread(symbolNum);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return readWordBuilder.toString();
    }

    public static Boolean IsReservedWord(String word)
    {
        String reservedWordRegExpr = "(implementation|resourcestring|initialization|unimplemented|saveregisters|alias|"+
                "dispinterface|nostackframe|experimental|finalization|constructor|reintroduce|reintroduce|destructor|specialize|oldfpccall|implements|enumerator|"+
                "deprecated|unaligned|softfloat|published|protected|otherwise|nodefault|interrupt|forward|continue|bitpacked|assembler|threadvar|procedure|"+
                "interface|inherited|function|absolute|safecall|register|platform|override|overload|noreturn|external|abstract|absolute|property|operator|boolean|"+
                "virtual|varargs|stdcall|private|message|iocheck|generic|dynamic|default|cppdecl|library|finally|exports|program|integer|downto|readln|winapi|strict|"+
                "stored|static|result|public|pascal|helper|export|packed|inline|except|string|repeat|record|packed|object|inline|const|begin|array|write|local|index|"+
                "far16|cdecl|break|raise|class|while|until|label|goto|file|case|char|real|read|near|name|cvar|with|uses|unit|type|then|self|for|end|else|div|asm|and|"+
                "far|try|out|xor|var|shr|shl|set|not|nil|mod|is|to|if|as|of|do|in|on|or)";
        Pattern pattern = Pattern.compile(reservedWordRegExpr);
        Matcher matcher = pattern.matcher(word);
        return matcher.find();
    }

    public static String ReadDigit(char curSymb, PushbackReader reader)
    {
        StringBuilder readWordBuilder = new StringBuilder(Character.toString(curSymb));
        try
        {
            int symbolNum = reader.read();
            while (symbolNum != -1 && (Character.isDigit((char)symbolNum) || (char)symbolNum == '.'))
            {
                readWordBuilder.append((char)symbolNum);
                symbolNum = reader.read();
            }
            if(symbolNum != -1)
                reader.unread(symbolNum);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return readWordBuilder.toString();
    }

    public static String ReadStringOrCharacter(char curSymb, PushbackReader reader)
    {
        StringBuilder readWordBuilder = new StringBuilder(Character.toString(curSymb));
        try
        {
            int symbolNum = reader.read();
            while (symbolNum != -1 && (char)symbolNum != '\'')
            {
                readWordBuilder.append((char)symbolNum);
                symbolNum = reader.read();
            }
            if(symbolNum != -1)
                readWordBuilder.append((char)symbolNum);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return readWordBuilder.toString();
    }

    public static String ReadCompilerDirective(char curSymb, PushbackReader reader)
    {
        StringBuilder readWordBuilder = new StringBuilder(Character.toString(curSymb));
        try
        {
            int symbolNum = reader.read();
            readWordBuilder.append((char)symbolNum);
            while (symbolNum != -1 && (char)symbolNum != '}')
            {
                readWordBuilder.append((char)symbolNum);
                symbolNum = reader.read();
            }
            if(symbolNum != -1)
                readWordBuilder.append((char)symbolNum);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return readWordBuilder.toString();
    }

    public static String ReadComment(char curSymb, PushbackReader reader)
    {
        StringBuilder readWordBuilder = new StringBuilder(Character.toString(curSymb));
        try
        {
            int symbolNum = reader.read();
            readWordBuilder.append((char)symbolNum);
            while (symbolNum != -1 && (char)symbolNum != ')')
            {
                readWordBuilder.append((char)symbolNum);
                symbolNum = reader.read();
            }
            if(symbolNum != -1)
                readWordBuilder.append((char)symbolNum);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return readWordBuilder.toString();
    }
}
