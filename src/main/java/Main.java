import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.core.interfaces.IExpr;

public class Main {
    /**
     * This class does not utilize the interpreter, instead take a look at the tests.
     */
    public static void main(String[] args) {
        ExprEvaluator util = new ExprEvaluator();

        // 3x + 12 = 46
        IExpr result = util.eval("Solve(3x + 12 == 46, x)");

        // print: {{x->34/3}}
        System.out.println(result);
    }
}