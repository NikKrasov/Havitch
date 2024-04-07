package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class AdditionMathExpression extends AbstractMathExpression {


    public AdditionMathExpression(AbstractExpression<Integer> firstOperand, AbstractExpression<Integer> secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public String getOperator() {
        return "add";
    }

    @Override    public Integer execute() {
        return getFirstOperand().execute()+getSecondOperand().execute();
    }
}
