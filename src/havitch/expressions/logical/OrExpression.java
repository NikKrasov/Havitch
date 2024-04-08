package havitch.expressions.logical;

import havitch.expressions.AbstractExpression;

public class OrExpression extends AbstractLogicalExpression {
    public OrExpression(AbstractExpression<Boolean> firstOperand, AbstractExpression<Boolean> secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public Boolean execute() {
        return getFirstOperand().execute() || getSecondOperand().execute();
    }
}
