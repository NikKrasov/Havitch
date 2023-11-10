package havitch.expressions;

import havitch.expressions.AbstractExpression;

public class ArgumentExpression extends AbstractExpression {

    private final int argument;

    public ArgumentExpression(int argument) {
        this.argument = argument;
    }

    @Override
    public int execute() {
        return argument;
    }
}
