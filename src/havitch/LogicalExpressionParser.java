package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.logical.AbstractLogicalExpression;
import havitch.expressions.logical.ArgumentBooleanExpression;
import havitch.expressions.logical.factories.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class LogicalExpressionParser {
    private final List<String> operatorsList = Arrays.asList("&", "|");
    Hashtable<String, AbstractLogicalCommandFactory> supportedOperatorCommandsFactories = new Hashtable<>() {
        {
            put("&", new AndFactory());
            put("|", new OrFactory());
        }
    };


    public AbstractExpression<Boolean> parse(String stringLogicalExpression) {
        ParsedTokens parsedTokens = parseTokens(stringLogicalExpression);
        // figure the sequence of operators.
        // First "and"
        // then "or"
        return buildExpressionThree(parsedTokens);
    }

    private AbstractExpression<Boolean> buildExpressionThree(ParsedTokens parsedTokens) {
        return buildExpressionThree(parsedTokens, null, 0);
    }

    private boolean isOperator(String operator) {
        return operatorsList.contains(operator);
    }

    private AbstractExpression<Boolean> buildExpressionThree(ParsedTokens parsedTokens, AbstractExpression<Boolean> parentExpression, int operatorInd) {

        var operator = parsedTokens.operatorTokens().get(operatorInd);
        // could be changed later
        AbstractExpression<Boolean> leftOperand = new ArgumentBooleanExpression(Boolean.parseBoolean(parsedTokens.operandTokens().get(operatorInd)));
        AbstractExpression<Boolean> rightOperand = new ArgumentBooleanExpression(Boolean.parseBoolean(parsedTokens.operandTokens().get(operatorInd+1)));
        if (parentExpression!=null) {
            if (isOperator(operator)) {
                // ??????
                return createExpression(operator, parentExpression, rightOperand);
            } else {
                // use left operand as a right operand for parent
                return leftOperand;
            }
        } else {
            

        }

        // last operator in expression. return last argument
        if (operatorInd==(parsedTokens.operatorTokens().size()-1))
        {
            rightOperand = createExpression(operator, leftOperand, rightOperand);
            return rightOperand;
        }
        var mathExpression = createExpression(operator, leftOperand, rightOperand);
        operatorInd++;
        buildExpressionThree(parsedTokens, mathExpression, operatorInd);


//        for (int operatorInd = 0; operatorInd < parsedTokens.operatorTokens().size(); operatorInd++) {
//            var operator = parsedTokens.operatorTokens().get(operatorInd);
//            var firstOperand = Integer.parseInt(parsedTokens.operandTokens().get(operatorInd));
//            int secondOperand = Integer.parseInt(parsedTokens.operandTokens().get(operatorInd+1));
//            if (multiplicationOperatorsList.contains(operator)){
//                if (mathExpression == null) {
//                    mathExpression = createExpression(operator, new ArgumentIntegerExpression(firstOperand), new ArgumentIntegerExpression(secondOperand));
//                    continue;
//                }
//                mathExpression = createExpression(operator, mathExpression, new ArgumentIntegerExpression(secondOperand));
//            } else {
//
//            }
//
//        }
        return mathExpression;
    }

    private AbstractLogicalExpression createExpression(String operator, AbstractExpression<Boolean> leftOperand, AbstractExpression<Boolean> rightOperand) {
        return supportedOperatorCommandsFactories.get(operator).create(leftOperand, rightOperand);
    }

    private ParsedTokens parseTokens(String mathExpression) {
        String operandToken = "";
        List<String> operandTokens = new ArrayList<>();
        List<String> operatorTokens = new ArrayList<>();
        for (char tokenChar :
                mathExpression.toCharArray()) {
            // ignore whitespaces
            if (Character.isWhitespace(tokenChar)) {
                continue;
            }
            var currentToken = new String(new char[tokenChar]);
            if (isOperator(currentToken)) {
                operandTokens.add(operandToken);
                operatorTokens.add(currentToken);
                operandToken = "";
                continue;
            }
            operandToken = operandToken + tokenChar;
        }
        return new ParsedTokens(operandTokens, operatorTokens);
    }

}
