package lib.types;

public class Num implements AST {
    private final Token<Integer> token;
    private final Integer value;

    public Integer getValue() {
        return value;
    }

    public Num(Token<Integer> token) {
        this.token = token;
        this.value = token.getValue();
    }
}
