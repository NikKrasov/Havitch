package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.AbstractMathExpression;
import havitch.expressions.math.ArgumentIntegerExpression;
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


    public AbstractExpression<Integer> parse(String stringMathExpression) {
        ParsedTokens parsedTokens = parseTokens(stringMathExpression);
        // figure the sequence of operators.
        // First multiplication or division
        // then addition or subtraction
        return buildMathExpressionThree(parsedTokens);
    }

    private AbstractExpression<Integer> buildMathExpressionThree(ParsedTokens parsedTokens) {
        return buildMathExpressionThree(parsedTokens, null, 0);
    }

    private boolean isExpressionOperator(String operator) {
        return expressionOperatorsList.contains(operator);
    }

    private AbstractExpression<Integer> buildMathExpressionThree(ParsedTokens parsedTokens, AbstractExpression<Integer> parentExpression, int operatorInd) {

//        AbstractMathExpression mathExpression = parentExpression;
        var operator = parsedTokens.operatorTokens().get(operatorInd);
        // could be changed later
        AbstractExpression<Integer> leftOperand = new ArgumentIntegerExpression(Integer.parseInt(parsedTokens.operandTokens().get(operatorInd)));
        AbstractExpression<Integer> rightOperand = new ArgumentIntegerExpression(Integer.parseInt(parsedTokens.operandTokens().get(operatorInd+1)));
        if (parentExpression!=null) {
            if (isExpressionOperator(operator)) {
                // ??????
                return createExpression(operator, parentExpression, rightOperand);
            } else {
                // use left operand as a right operand for parent
                return leftOperand;
            }
//            leftOperand = parentExpression;
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
        buildMathExpressionThree(parsedTokens, mathExpression, operatorInd);













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

    private AbstractMathExpression createExpression(String operator, AbstractExpression leftOperand, AbstractExpression rightOperand) {
        return supportedOperatorCommandsFactories.get(operator).create(leftOperand, rightOperand);
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
