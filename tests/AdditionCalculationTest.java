import havitch.expressions.math.ArgumentIntegerExpression;
import havitch.expressions.math.AdditionMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdditionCalculationTest {

    @Test
    void calculateTest() {

        AdditionMathExpression command = new AdditionMathExpression(new ArgumentIntegerExpression(2), new ArgumentIntegerExpression(3));
        assertEquals(5, command.execute());
    }
}