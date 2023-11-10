package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class AdditionMathExpression extends AbstractMathExpression {


    public AdditionMathExpression(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    @Override
    public String getOperator() {
        return "add";
    }

    @Override    public int execute() {
        return getFirstOperand().execute()+getSecondOperand().execute();
    }
}
