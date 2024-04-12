package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.logical.AndExpression;
import havitch.expressions.logical.ArgumentBooleanExpression;
import havitch.expressions.logical.OrExpression;
import havitch.expressions.logical.factories.AbstractLogicalCommandFactory;
import havitch.expressions.logical.factories.AndFactory;
import havitch.expressions.logical.factories.OrFactory;

import java.util.*;

import static havitch.LogicalExpressionParser.AND_OPERATOR;
import static havitch.LogicalExpressionParser.OR_OPERATOR;

public class LogicalExpressionBuilder {


    Hashtable<String, AbstractLogicalCommandFactory> supportedOperatorCommandsFactories = new Hashtable<>() {
        {
            put(AND_OPERATOR, new AndFactory());
            put(OR_OPERATOR, new OrFactory());
        }
    };

    public AbstractExpression<Boolean> build(ParsedTokens parsedTokens) {
        return buildExpressionThree(parsedTokens, null, 0);

    }

    private AbstractExpression<Boolean> buildExpressionThree(ParsedTokens parsedTokens, AbstractExpression<Boolean> rightOperand, int operatorIndex) {
        AbstractExpression<Boolean> firstOperand = rightOperand == null ? new ArgumentBooleanExpression(false) : rightOperand;
        List<String> operatorTokens = parsedTokens.operatorTokens();
        if (operatorIndex >= operatorTokens.size()) {
            return new OrExpression(
                    firstOperand,
                    rightOperandByOperatorIndex(parsedTokens, operatorIndex - 1));
        }
        AbstractExpression<Boolean> secondOperand = null;
        int i = operatorIndex;
        String operator = operatorTokens.get(i);
        while (operator.equals(AND_OPERATOR)) {
            secondOperand = new AndExpression(
                    secondOperand == null ? leftOperandByOperatorIndex(parsedTokens, i) : secondOperand,
                    rightOperandByOperatorIndex(parsedTokens, i));
            i++;
            if (i >= operatorTokens.size()) {
                break;
            }
            operator = operatorTokens.get(i);
        }
        if (i < operatorTokens.size()) {
            // get second operand by looking at next operator.
            // In case next operator is AND, it will be returned as and expression from the recursion
            secondOperand = buildExpressionThree(
                    parsedTokens,
                    secondOperand == null ? leftOperandByOperatorIndex(parsedTokens, i) : secondOperand,
                    i + 1);
        };

        return new OrExpression(
                firstOperand,
                secondOperand);
    }


    private AbstractExpression<Boolean> leftOperandByOperatorIndex(ParsedTokens parsedTokens, int operatorIndex) {
        return new ArgumentBooleanExpression(Boolean.parseBoolean(parsedTokens.operandTokens().get(operatorIndex)));
    }

    private AbstractExpression<Boolean> rightOperandByOperatorIndex(ParsedTokens parsedTokens, int operatorIndex) {
        return new ArgumentBooleanExpression(Boolean.parseBoolean(parsedTokens.operandTokens().get(operatorIndex + 1)));
    }

}
