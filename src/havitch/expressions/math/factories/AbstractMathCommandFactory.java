package havitch.expressions.math.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;

public abstract class AbstractMathCommandFactory {
    public abstract AbstractMathExpression create(AbstractExpression firstOperand, AbstractExpression secondOperand);
}
