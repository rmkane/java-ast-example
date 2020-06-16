package lib.types;

public class Token<T> {
    private final TokenType type;
    private final T value;

    public TokenType getType() {
        return type;
    }

    public T getValue() {
        return value;
    }

    public Token(TokenType type, T value) {
        this.type = type;
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Token(%s, %s)", type, value);
    }
}
