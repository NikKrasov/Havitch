package havitch.expressions.logical;

import havitch.expressions.AbstractExpression;

public class ArgumentBooleanExpression extends AbstractExpression<Boolean> {

    private final Boolean booleanValue;
    public ArgumentBooleanExpression(Boolean booleanValue){

        this.booleanValue = booleanValue;
    }
    @Override
    public Boolean execute() {
        return booleanValue;
    }
}
