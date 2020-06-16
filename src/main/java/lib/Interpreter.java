package lib;

import lib.types.AST;
import lib.types.BinOp;
import lib.types.Num;

public class Interpreter {
    private Parser parser;

    public Interpreter(Parser parser) {
        this.parser = parser;
    }

    private int visit(AST node) {
        if (node instanceof BinOp) {
            return visitBinOp((BinOp) node);
        } else if (node instanceof Num) {
            return visitNum((Num) node);
        }

        throw new IllegalArgumentException("No visit method for " + node.getClass().getSimpleName());
    }

    protected int visitBinOp(BinOp node) {
        switch (node.getOp().getType()) {
            case PLUS:
                return this.visit(node.getLeft()) + this.visit(node.getRight());
            case MINUS:
                return this.visit(node.getLeft()) - this.visit(node.getRight());
            case MUL:
                return this.visit(node.getLeft()) * this.visit(node.getRight());
            case DIV:
                return this.visit(node.getLeft()) / this.visit(node.getRight());
        }
        return 0;
    }

    protected int visitNum(Num node) {
        return node.getValue();
    }


    public int interpret() {
        AST tree = this.parser.parse();
        return this.visit(tree);
    }
}
