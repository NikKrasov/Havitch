package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicalExpressionParserTest {

    private static AbstractExpression<Boolean> subject(String expressionString) {
        return new LogicalExpressionParser().parse(expressionString);
    }


    @Test
    void true_or_false() {
        AbstractExpression<Boolean> expression = subject("true|false");
        var result = expression.execute();
        assertTrue(result);
    }
    @Test
    void true_or_true() {
        AbstractExpression<Boolean> expression = subject("true|true");
        var result = expression.execute();
        assertTrue(result);
    }
    @Test
    void false_or_false() {
        AbstractExpression<Boolean> expression = subject("false|false");
        var result = expression.execute();
        assertFalse(result);
    }
    @Test
    void false_or_true() {
        AbstractExpression<Boolean> expression = subject("false|true");
        var result = expression.execute();
        assertTrue(result);
    }
    @Test
    void true_and_true() {
        AbstractExpression<Boolean> expression = subject("true&true");
        var result = expression.execute();
        assertTrue(result);
    }

    @Test
    void true_and_false() {
        AbstractExpression<Boolean> expression = subject("true&false");
        var result = expression.execute();
        assertFalse(result);
    }
    @Test
    void false_and_false() {
        AbstractExpression<Boolean> expression = subject("false&false");
        var result = expression.execute();
        assertFalse(result);
    }

    @Test
    void false_and_true() {
        AbstractExpression<Boolean> expression = subject("false&true");
        var result = expression.execute();
        assertFalse(result);
    }

    @Test
    void false_or_true_and_true() {
        AbstractExpression<Boolean> expression = subject("false|true&true");
        var result = expression.execute();
        assertTrue(result);
    }

    @Test
    void false_or_true_and_false() {
        AbstractExpression<Boolean> expression = subject("false|true&false");
        var result = expression.execute();
        assertFalse(result);
    }

    @Test
    void false_or_true_and_false_or_true() {
        AbstractExpression<Boolean> expression = subject("false|true&false|true");
        var result = expression.execute();
        assertTrue(result);
    }

    @Test
    void false_or_true_and_false_or_false() {
        AbstractExpression<Boolean> expression = subject("false|true&false|false");
        var result = expression.execute();
        assertFalse(result);
    }

    @Test
    void false_or_true_and_true_or_false() {
        AbstractExpression<Boolean> expression = subject("false|true&true|false");
        var result = expression.execute();
        assertTrue(result);
    }

    @Test
    void false_or_true_and_true_or_false_or_false_and_true() {
        AbstractExpression<Boolean> expression = subject("false|true&false|false|false&true");
        var result = expression.execute();
        assertFalse(result);
    }

    @Test
    void false_or_true_and_true_or_false_or_true_and_true() {
        AbstractExpression<Boolean> expression = subject("false|true&false|false|true&true");
        var result = expression.execute();
        assertTrue(result);
    }

    @Test
    void false_or_false_or_false_or_false_or_false_or_true() {
        AbstractExpression<Boolean> expression = subject("false|false|false|false|false|true");
        var result = expression.execute();
        assertTrue(result);
    }

    @Test
    void true_and_true_or_false_or_false_or_false_or_false_or_false() {
        AbstractExpression<Boolean> expression = subject("true&true|false|false|false|false|false|false");
        var result = expression.execute();
        assertTrue(result);
    }

}