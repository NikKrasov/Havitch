package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class DivisionMathExpression extends AbstractMathExpression {
    public DivisionMathExpression(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        super(firstOperand, secondOperand);
        if (secondOperand==0) {
            throw new IllegalArgumentException("Second operand cannot be 0. Math not allowing to divide by 0. Change second operand ant try again");
        }
    }

    @Override
    public int execute() {
        return getFirstOperand()/getSecondOperand();
    }

    @Override
    public String getOperator() {
        return null;
    }
}
