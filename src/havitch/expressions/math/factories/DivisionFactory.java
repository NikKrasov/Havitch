package havitch.expressions.math.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import havitch.expressions.math.DivisionMathExpression;

public class DivisionFactory extends AbstractMathCommandFactory {
    @Override
    public AbstractMathExpression create(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        return new DivisionMathExpression(firstOperand,secondOperand);
    }
}
