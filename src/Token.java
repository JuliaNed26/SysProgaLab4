public class Token
{
    final public TokenType tokenType;
    final public String value;

    public Token(TokenType _tokenType, String _value)
    {
        tokenType = _tokenType;
        value = _value;
    }

    public String toString()
    {
        return "<"+tokenType+"> - <"+value+">";
    }
}
