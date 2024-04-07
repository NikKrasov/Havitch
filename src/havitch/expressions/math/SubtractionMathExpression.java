package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class SubtractionMathExpression extends AbstractMathExpression {
    public SubtractionMathExpression(AbstractExpression<Integer> firstOperand, AbstractExpression<Integer> secondOperand) {
        super(firstOperand, secondOperand);
    }

    public Integer execute(){
        return getFirstOperand().execute()-getSecondOperand().execute();
    }

    @Override
    public String getOperator() {
        return "sub";
    }
}
