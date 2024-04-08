package havitch.expressions.logical.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.logical.AbstractLogicalExpression;
import havitch.expressions.logical.OrExpression;

public class OrFactory extends AbstractLogicalCommandFactory {
    @Override
    public AbstractLogicalExpression create(AbstractExpression<Boolean> firstOperand, AbstractExpression<Boolean> secondOperand) {
        return new OrExpression(firstOperand, secondOperand);
    }
}
