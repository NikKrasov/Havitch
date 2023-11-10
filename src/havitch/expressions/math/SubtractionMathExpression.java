package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class SubtractionMathExpression extends AbstractMathExpression {
    public SubtractionMathExpression(AbstractExpression firstOperand, AbstractExpression secondOperand) {
        super(firstOperand, secondOperand);
    }

    public int execute(){
        return getFirstOperand()-getSecondOperand();
    }

    @Override
    public String getOperator() {
        return "sub";
    }
}
