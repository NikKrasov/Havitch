import havitch.expressions.math.ArgumentIntegerExpression;
import havitch.expressions.math.DivisionMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionCalculationTest {

    @Test
    void divisionTest() {
        assertEquals(5,new DivisionMathExpression(new ArgumentIntegerExpression(10), new ArgumentIntegerExpression(2)).execute());
    }

}