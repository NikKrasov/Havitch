package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class MultiplicationMathExpression extends AbstractMathExpression {

    public MultiplicationMathExpression(AbstractExpression<Integer> firstOperand, AbstractExpression<Integer> secondOperand) {
        super(firstOperand, secondOperand);
    }
    @Override
    public String getOperator() {
         return "multi";
    }

    @Override
    public Integer execute() {
        return getFirstOperand().execute() * getSecondOperand().execute();
    }
}
