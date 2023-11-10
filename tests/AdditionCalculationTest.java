import havitch.expressions.math.AdditionMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionCalculationTest {

    @Test
    void calculateTest() {

        AdditionMathExpression command = new AdditionMathExpression(2, 3);
        assertEquals(5, command.execute());
    }
}