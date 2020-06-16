import lib.Interpreter;
import lib.Lexer;
import lib.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class InterpreterTest {
    @Test
    public void testInterpreter() {
        Map<String, Integer> testCases = new HashMap<>();

        testCases.put("7 + 3 * (10 / (12 / (3 + 1) - 1))", 22);
        testCases.put("7 + 3 * (10 / (12 / (3 + 1) - 1)) / (2 + 3) - 5 - 3 + (8)", 10);
        testCases.put("7 + (((3 + 2)))", 12);

        testCases.entrySet().forEach(entry -> {
            Assert.assertEquals(entry.getValue().intValue(), evaluateExpression(entry.getKey()));
        });
    }

    private int evaluateExpression(String expression) {
        Lexer lexer = new Lexer(expression);
        Parser parser = new Parser(lexer);
        Interpreter interpreter = new Interpreter(parser);
        return interpreter.interpret();
    }
}
