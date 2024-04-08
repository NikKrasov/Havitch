package havitch.expressions.logical;

import havitch.expressions.AbstractExpression;

public class AndExpression extends AbstractLogicalExpression{
    public AndExpression(AbstractExpression<Boolean> firstOperand, AbstractExpression<Boolean> secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public Boolean execute() {
        return getFirstOperand().execute() && getSecondOperand().execute();
    }
}
