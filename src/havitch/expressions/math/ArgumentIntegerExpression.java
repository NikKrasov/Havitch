package havitch.expressions.math;

import havitch.expressions.AbstractExpression;

public class ArgumentIntegerExpression extends AbstractExpression<Integer> {

    private final Integer argument;

    public ArgumentIntegerExpression(Integer argument) {
        this.argument = argument;
    }

    @Override
    public Integer execute() {
        return argument;
    }
}
