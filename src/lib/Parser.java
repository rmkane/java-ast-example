package lib;

import lib.types.*;

public class Parser {
    private Lexer lexer;
    private Token currToken;

    public Parser(Lexer lexer) {
        this.lexer = lexer;
        this.currToken = this.lexer.getNextToken();
    }

    private void error() {
        throw new IllegalArgumentException("Invalid syntax");
    }

    private void eat(TokenType token_type) {
        if (this.currToken.getType() == token_type) {
            this.currToken = this.lexer.getNextToken();
        } else {
            this.error();
        }
    }

    private AST factor() {
        Token token = this.currToken;

        if (token.getType() == TokenType.INTEGER) {
            this.eat(TokenType.INTEGER);
            return new Num(token);
        } else if (token.getType() == TokenType.LPAREN) {
            this.eat(TokenType.LPAREN);
            AST node = this.expr();
            this.eat(TokenType.RPAREN);
            return node;
        }

        return null;
    }

    private AST term() {
        AST node = this.factor();

        while (isType(this.currToken.getType(), TokenType.MUL, TokenType.DIV)) {
            Token token = this.currToken;
            if (token.getType() == TokenType.MUL) {
                this.eat(TokenType.MUL);
            } else if (token.getType() == TokenType.DIV) {
                this.eat(TokenType.DIV);
            }

            node = new BinOp(node, token, this.factor());
        }

        return node;
    }

    private AST expr() {
        AST node = this.term();

        while (isType(this.currToken.getType(), TokenType.PLUS, TokenType.MINUS)) {
            Token token = this.currToken;
            if (token.getType() == TokenType.PLUS) {
                this.eat(TokenType.PLUS);
            } else if (token.getType() == TokenType.MINUS) {
                this.eat(TokenType.MINUS);
            }

            node = new BinOp(node, token, this.term());
        }

        return node;
    }

    private boolean isType(TokenType type, TokenType... types) {
        for (TokenType currType : types) {
            if (type == currType) {
                return true;
            }
        }
        return false;
    }

    public AST parse() {
        return this.expr();
    }
}
