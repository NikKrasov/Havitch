package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public abstract class AbstractMathExpression extends AbstractExpression {


    private final AbstractExpression firstOperand;
    private final AbstractExpression secondOperand;

    public AbstractMathExpression(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        this.firstOperand = firstOperand;
        this.secondOperand = secondOperand;
    }

    public abstract String getOperator();


    public AbstractExpression getFirstOperand() {
        return firstOperand;
    }

    public AbstractExpression getSecondOperand() {
        return secondOperand;
    }

}
