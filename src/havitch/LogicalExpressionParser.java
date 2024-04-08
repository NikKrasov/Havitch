package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.logical.AndExpression;
import havitch.expressions.logical.ArgumentBooleanExpression;
import havitch.expressions.logical.OrExpression;
import havitch.expressions.logical.factories.AbstractLogicalCommandFactory;
import havitch.expressions.logical.factories.AndFactory;
import havitch.expressions.logical.factories.OrFactory;

import java.util.*;

public class LogicalExpressionParser {

    private static final String AND_OPERATOR = "&";
    private static final String OR_OPERATOR = "|";
    private final List<String> operatorsList = Arrays.asList(AND_OPERATOR, OR_OPERATOR);
    Hashtable<String, AbstractLogicalCommandFactory> supportedOperatorCommandsFactories = new Hashtable<>() {
        {
            put(AND_OPERATOR, new AndFactory());
            put(OR_OPERATOR, new OrFactory());
        }
    };

    public AbstractExpression<Boolean> parse(String stringLogicalExpression) {
        ParsedTokens parsedTokens = parseTokens(stringLogicalExpression);
        return buildExpressionThree(parsedTokens);
    }

    private AbstractExpression<Boolean> buildExpressionThree(ParsedTokens parsedTokens) {
        return normalizeExpression(parsedTokens, null, 0);
    }

    private boolean isOperator(String operator) {
        return operatorsList.contains(operator);
    }

    private AbstractExpression<Boolean> normalizeExpression(ParsedTokens parsedTokens, AbstractExpression<Boolean> rightOperand, int operatorIndex) {
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
            secondOperand = normalizeExpression(
                    parsedTokens,
                    secondOperand == null ? leftOperandByOperatorIndex(parsedTokens, i) : secondOperand,
                    i + 1);
        };
        firstOperand = new OrExpression(
                firstOperand,
                secondOperand);

        return firstOperand;
    }


    private AbstractExpression<Boolean> leftOperandByOperatorIndex(ParsedTokens parsedTokens, int operatorIndex) {
        return new ArgumentBooleanExpression(Boolean.parseBoolean(parsedTokens.operandTokens().get(operatorIndex)));
    }

    private AbstractExpression<Boolean> rightOperandByOperatorIndex(ParsedTokens parsedTokens, int operatorIndex) {
        return new ArgumentBooleanExpression(Boolean.parseBoolean(parsedTokens.operandTokens().get(operatorIndex + 1)));
    }

    private ParsedTokens parseTokens(String logicalExpression) {
        String operandToken = "";
        List<String> operandTokens = new ArrayList<>();
        List<String> operatorTokens = new ArrayList<>();
        for (char tokenChar :
                logicalExpression.toCharArray()) {
            // ignore whitespaces
            if (Character.isWhitespace(tokenChar)) {
                continue;
            }
            var currentToken = String.valueOf(tokenChar);
            if (isOperator(currentToken)) {
                operandTokens.add(operandToken);
                operatorTokens.add(currentToken);
                operandToken = "";
                continue;
            }
            operandToken = operandToken + tokenChar;
        }
        operandTokens.add(operandToken);
        return new ParsedTokens(operandTokens, operatorTokens);
    }

}
