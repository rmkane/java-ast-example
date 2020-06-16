package lib;

import lib.types.Token;
import lib.types.TokenType;

public class Lexer {
    private String text;
    private int pos;
    private Character currChar;

    public Lexer(String text) {
        this.text = text;
        this.pos = 0;
        this.currChar = this.text.charAt(this.pos);
    }

    private void error() {
        throw new IllegalArgumentException("Invalid character");
    }

    private void advance() {
        this.pos += 1;
        if (this.pos > this.text.length() - 1)
            this.currChar = null;
        else
            this.currChar = this.text.charAt(this.pos);
    }

    private void skipWhitespace() {
        while (this.currChar != null && Character.isWhitespace(this.currChar)) {
            this.advance();
        }
    }

    private int integer() {
        String result = "";
        while (this.currChar != null && Character.isDigit(this.currChar)) {
            result += this.currChar;
            this.advance();
        }
        return Integer.parseInt(result, 10);
    }

    public Token getNextToken() {
        while (this.currChar != null) {
            if (Character.isWhitespace(this.currChar)) {
                this.skipWhitespace();
                continue;
            }

            if (Character.isDigit(this.currChar)) {
                return new Token(TokenType.INTEGER, this.integer());
            }

            switch (this.currChar) {
                case '+':
                    this.advance();
                    return new Token(TokenType.PLUS, '+');
                case '-':
                    this.advance();
                    return new Token(TokenType.MINUS, '-');
                case '*':
                    this.advance();
                    return new Token(TokenType.MUL, '*');
                case '/':
                    this.advance();
                    return new Token(TokenType.DIV, '/');
                case '(':
                    this.advance();
                    return new Token(TokenType.LPAREN, '(');
                case ')':
                    this.advance();
                    return new Token(TokenType.RPAREN, ')');
                default:
                    this.error();
            }
        }

        return new Token(TokenType.EOF, null);
    }
}
