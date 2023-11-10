import havitch.expressions.math.DivisionMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DivisionCalculationTest {

    @Test
    void divisionTest() {
        assertEquals(5,new DivisionMathExpression(10,2).execute());
    }

    @Test
    void divisionByZeroTest(){
        assertThrows(IllegalArgumentException.class, () -> new DivisionMathExpression(5, 0));
    }
}