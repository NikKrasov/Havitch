import havitch.expressions.math.ArgumentIntegerExpression;
import havitch.expressions.math.MultiplicationMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MultiplicationCalculationTest {

    @Test
    void multiplyTest(){
        assertEquals(9, new MultiplicationMathExpression(new ArgumentIntegerExpression(3), new ArgumentIntegerExpression(3)).execute());
    }

}