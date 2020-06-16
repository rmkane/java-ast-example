package lib.types;

public class BinOp implements AST {
    private final AST left;
    private final AST right;
    private final Token token;
    private final Token op;

    public AST getLeft() {
        return left;
    }

    public AST getRight() {
        return right;
    }

    public Token getOp() {
        return op;
    }

    public BinOp(AST left, Token op, AST right) {
        this.left = left;
        this.token = this.op = op;
        this.right = right;
    }
}
