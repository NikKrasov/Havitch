package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class MultiplicationMathExpression extends AbstractMathExpression {

    public MultiplicationMathExpression(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        super(firstOperand, secondOperand);
    }
    @Override
    public String getOperator() {
         return "multi";
    }

    @Override
    public int execute() {
        return getFirstOperand().execute() * getSecondOperand().execute();
    }
}
