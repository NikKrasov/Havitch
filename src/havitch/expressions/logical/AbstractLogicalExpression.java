package havitch.expressions.logical;

import havitch.expressions.AbstractExpression;

public abstract class AbstractLogicalExpression extends AbstractExpression<Boolean> {

    private final AbstractExpression<Boolean> firstOperand;
    private final AbstractExpression<Boolean> secondOperand;

    public AbstractLogicalExpression(AbstractExpression<Boolean> firstOperand, AbstractExpression<Boolean> secondOperand){

        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }
    @Override
    public abstract Boolean execute();

    public AbstractExpression<Boolean> getFirstOperand() {
        return firstOperand;
    }

    public AbstractExpression<Boolean> getSecondOperand() {
        return secondOperand;
    }
}
