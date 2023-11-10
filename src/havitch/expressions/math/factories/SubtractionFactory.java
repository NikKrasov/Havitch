package havitch.expressions.math.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import havitch.expressions.math.SubtractionMathExpression;

public class SubtractionFactory extends AbstractMathCommandFactory {
    public AbstractMathExpression create(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        return new SubtractionMathExpression(firstOperand, secondOperand);
    }
}
