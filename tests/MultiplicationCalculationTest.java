import havitch.expressions.math.MultiplicationMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationCalculationTest {

    @Test
    void multiplyTest(){
        assertEquals(9, new MultiplicationMathExpression(3,3).execute());
    }

}