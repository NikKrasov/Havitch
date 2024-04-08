package havitch.expressions.logical.factories;

import havitch.expressions.AbstractExpression;
import havitch.expressions.logical.AbstractLogicalExpression;

public abstract class AbstractLogicalCommandFactory {
    public abstract AbstractLogicalExpression create(AbstractExpression<Boolean> firstOperand, AbstractExpression<Boolean> secondOperand);

}
