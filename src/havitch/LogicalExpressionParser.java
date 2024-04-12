package havitch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LogicalExpressionParser {
    public static final String AND_OPERATOR = "&";
    public static final String OR_OPERATOR = "|";
    public final List<String> operatorsList = Arrays.asList(AND_OPERATOR, OR_OPERATOR);

    public ParsedTokens parseStringExpression(String logicalExpression) {
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

    private boolean isOperator(String operator) {
        return operatorsList.contains(operator);
    }

}
