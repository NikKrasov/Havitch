package havitch;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicalExpressionParserTest {



    @Test
    void can_parse_true_or_false() {
        var tokens = new LogicalExpressionParser().parseStringExpression("true|false");
        assertEquals(2, tokens.operandTokens().size());
        assertEquals(1, tokens.operatorTokens().size());
    }

    @Test
    void returns_distinct_operands_count() {
        var tokens = new LogicalExpressionParser().parseStringExpression("a&a&b&c&a");
        assertEquals(3, tokens.distinctOperands());
    }

}