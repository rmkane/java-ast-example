import lib.*;

import java.util.Arrays;
import java.util.List;

/**
 * See: https://ruslanspivak.com/lsbasi-part7/
 */
public class Main {
    public static void main(String[] args) {
        List<String> expressions = Arrays.asList(
            "7 + 3 * (10 / (12 / (3 + 1) - 1))", // 22
            "7 + 3 * (10 / (12 / (3 + 1) - 1)) / (2 + 3) - 5 - 3 + (8)", // 10
            "7 + (((3 + 2)))" // 12
        );

        expressions.forEach(expr -> System.out.println(evaluateExpression(expr)));
    }

    public static int evaluateExpression(String expression) {
        Lexer lexer = new Lexer(expression);
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        return interpreter.interpret();
    }
}
