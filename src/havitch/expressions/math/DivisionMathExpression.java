package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class DivisionMathExpression extends AbstractMathExpression {
    public DivisionMathExpression(AbstractExpression<Integer> firstOperand, AbstractExpression<Integer> secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public Integer execute() {
        return getFirstOperand().execute()/getSecondOperand().execute();
    }

    @Override
    public String getOperator() {
        return null;
    }
}
