package havitch.expressions.math.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import havitch.expressions.math.AdditionMathExpression;

public class AdditionFactory extends AbstractMathCommandFactory {
    public AbstractMathExpression create(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        return new AdditionMathExpression(firstOperand, secondOperand);
    }
}
