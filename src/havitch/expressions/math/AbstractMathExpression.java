package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public abstract class AbstractMathExpression extends AbstractExpression<Integer> {


    private final AbstractExpression<Integer> firstOperand;
    private final AbstractExpression<Integer> secondOperand;

    public AbstractMathExpression(AbstractExpression<Integer> firstOperand, AbstractExpression<Integer> secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public abstract String getOperator();


    public AbstractExpression<Integer> getFirstOperand() {
        return firstOperand;
    }

    public AbstractExpression<Integer> getSecondOperand() {
        return secondOperand;
    }

}
