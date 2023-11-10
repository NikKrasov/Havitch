package havitch;

import havitch.expressions.math.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MathExpressionParserTest {

    public static final String MATH_EXPRESSION = "4+23*6/2-5";

    private static AbstractMathExpression[] subject() {
        return new MathExpressionParser().parse(MATH_EXPRESSION);
    }

    @Test
    void parse_returns_commands_Test() {
        AbstractMathExpression[] commands = subject();
        assertEquals(commands.length, 4);
    }


    @Test
    void parse_returns_add_command(){
        AbstractMathExpression[] commands = subject();
        assertParsedCommand(commands[0], AdditionMathExpression.class,4, 23);
    }

    @Test
    void parse_returns_multi_command(){
        AbstractMathExpression[] commands = subject();
        assertParsedCommand(commands[1], MultiplicationMathExpression.class,23, 6);
    }

    @Test
    void parse_return_division_command() {
        var commands = subject();
        assertParsedCommand(commands[2], DivisionMathExpression.class, 6, 2);
    }

    @Test
    void parse_return_sub_command() {
        var commands = subject();
        assertParsedCommand(commands[3], SubtractionMathExpression.class, 2, 5);
    }
    private static void assertParsedCommand(AbstractMathExpression command, Class mathCommand, int exepectedFirstOperand, int expetedSecondOperand) {
        assertEquals(command.getClass(), mathCommand);
        assertEquals(command.getFirstOperand(), exepectedFirstOperand);
        assertEquals(command.getSecondOperand(), expetedSecondOperand);
    }

}