package havitch.expressions.logical.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.logical.AbstractLogicalExpression;
import havitch.expressions.logical.AndExpression;

public class AndFactory extends AbstractLogicalCommandFactory{
    @Override
    public AbstractLogicalExpression create(AbstractExpression<Boolean> firstOperand, AbstractExpression<Boolean> secondOperand) {
        return new AndExpression(firstOperand, secondOperand);
    }
}
