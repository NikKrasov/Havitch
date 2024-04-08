import havitch.expressions.math.ArgumentIntegerExpression;
import havitch.expressions.math.SubtractionMathExpression;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubtractionMathCommandTest {

    @Test
    void subtractionTest() {
        var subtraction = new SubtractionMathExpression(new ArgumentIntegerExpression(5), new ArgumentIntegerExpression(2));
        assertEquals(3, subtraction.execute());
    }

}