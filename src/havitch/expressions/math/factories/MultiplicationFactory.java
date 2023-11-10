package havitch.expressions.math.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import havitch.expressions.math.MultiplicationMathExpression;

public class MultiplicationFactory extends AbstractMathCommandFactory {
    @Override
    public AbstractMathExpression create(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        return new MultiplicationMathExpression(firstOperand,secondOperand);
    }
}
