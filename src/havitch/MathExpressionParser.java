package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import havitch.expressions.ArgumentExpression;
import havitch.expressions.math.factories.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

public class MathExpressionParser {
    private final List<String> signOperatorsList = Arrays.asList("+", "-");
    private final List<String> expressionOperatorsList = Arrays.asList("*", "/");
    Hashtable<String, AbstractMathCommandFactory> supportedOperatorCommandsFactories = new Hashtable<>() {
        {
            put("+", new AdditionFactory());
            put("-", new SubtractionFactory());
            put("/", new DivisionFactory());
            put("*", new MultiplicationFactory());
        }
    };
    private final List<String> multiplicationOperatorsList = Arrays.asList("*", "/");


    public AbstractExpression parse(String stringMathExpression) {
        ParsedTokens parsedTokens = parseTokens(stringMathExpression);
        // figure the sequence of operators.
        // First multiplication or division
        // then addition or subtraction
        return createMathExpression(parsedTokens);
    }

    private AbstractExpression createMathExpression(ParsedTokens parsedTokens) {
        return createMathExpression(parsedTokens, null, 0);
    }

    private boolean isExpressionOperator(String operator) {
        return expressionOperatorsList.contains(operator);
    }

    private AbstractExpression createMathExpression(ParsedTokens parsedTokens, AbstractExpression parentExpression, int operatorInd) {

//        AbstractMathExpression mathExpression = parentExpression;
        var operator = parsedTokens.operatorTokens().get(operatorInd);
        if (isExpressionOperator(operator)) {

        }
        AbstractExpression leftOperand = new ArgumentExpression(Integer.parseInt(parsedTokens.operandTokens().get(operatorInd)));
        if (parentExpression!=null) {
            leftOperand = parentExpression;
        }

        // could be changed later
        AbstractExpression rightOperand = new ArgumentExpression(Integer.parseInt(parsedTokens.operandTokens().get(operatorInd+1)));
        // last operator in expression. return last argument
        if (operatorInd==(parsedTokens.operatorTokens().size()-1))
        {
            rightOperand = supportedOperatorCommandsFactories.get(operator).create(leftOperand, rightOperand);
            return rightOperand;
        }
        var mathExpression = supportedOperatorCommandsFactories.get(operator).create(leftOperand, rightOperand);
        operatorInd++;
        createMathExpression(parsedTokens, mathExpression, operatorInd);
        return mathExpression;
    }

    private ParsedTokens parseTokens(String mathExpression) {
        String token = "";
        List<String> operandTokens = new ArrayList<String>();
        List<String> operatorTokens = new ArrayList<String>();
        for (char tokenChar :
                mathExpression.toCharArray()) {
            // ignore whitespaces
            if (Character.isWhitespace(tokenChar)) {
                continue;
            }
            if (Character.isDigit(tokenChar)) {
                token = token + tokenChar;
            } else {
                operandTokens.add(token);
                operatorTokens.add(new String(new char [tokenChar]));
                token = "";
            }
        }
        return new ParsedTokens(operandTokens, operatorTokens);
    }
}
