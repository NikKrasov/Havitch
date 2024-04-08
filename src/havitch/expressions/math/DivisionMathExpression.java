package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class DivisionMathExpression extends AbstractMathExpression {
    public DivisionMathExpression(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public int execute() {
        return getFirstOperand().execute()/getSecondOperand().execute();
    }

    @Override
    public String getOperator() {
        return null;
    }
}
