package havitch;

import havitch.expressions.AbstractExpression;
import havitch.expressions.math.factories.*;
import java.util.List;
import java.util.*;

public class Start {
    public Start(){
    }
    String username;
    String lifeInf;

    Hashtable<String, AbstractMathCommandFactory> supportedOperatorCommands = new Hashtable<>() {
        {
            put("add", new AdditionFactory());
            put("sub", new SubtractionFactory());
            put("div", new DivisionFactory());
            put("multi", new MultiplicationFactory());
        }
    };
    public static void main(String[] args) {
        Start m = new Start();
        var logicalExpressionEnteredByUser = m.getLineInfo();
        m.act(logicalExpressionEnteredByUser);
    }
    public void  act(String expressionEnteredByUser){
//        welcome();
//        username = getLineInfo();
//        education(username);
//        var result = new MathExpressionParser().parse(inputMathExpression()).execute();
//        System.out.println("Calculated result: ");
//        System.out.println(result);
        var parsedVariableTokens = new LogicalExpressionParser().parseStringExpression(expressionEnteredByUser);
        var operandValues = new TruthTable().makeTable(parsedVariableTokens);
        var calculatedTruthTable = calculateTruthTable(parsedVariableTokens, operandValues);
        printTable(calculatedTruthTable);
    }

    private void printTable(String[][] table) {
        for (String[] row :
                table) {
            System.out.print(" ");
            for (String cell :
                    row) {
                System.out.print(cell + " | ");
            }
            printRowDelimeter(row.length);

        }

    }

    private static void printRowDelimeter(int columnsCount) {
        System.out.println(" ");

        for (int i =0; i<columnsCount; i++) {
            System.out.print("---|");
        }
        System.out.println();
    }

    private String[][] calculateTruthTable(ParsedTokens parsedVariableTokens, String[][] operandValues) {
        HashMap<String,String> nameAndValue = new HashMap<String,String>();
        var calculatedTruthTable = new String[operandValues.length][operandValues[0].length+1];
        calculatedTruthTable[0] = createHeader(operandValues[0]);

        for (int row = 1; row < operandValues.length; row++) {
            List<String> parsedVariableValues = new ArrayList<String>();
            String[] rowWithOperandValues = operandValues[row];
            int column = 0;
            while (column < rowWithOperandValues.length) {
                nameAndValue.put(operandValues[0][column], operandValues[row][column]);
                column = column + 1;
            }
            List<String> operandTokens = parsedVariableTokens.operandTokens();
            for (int i = 0; i < operandTokens.size(); i++) {
                String variableName = operandTokens.get(i);
                String variableValue = nameAndValue.get(variableName);
                parsedVariableValues.add(variableValue);
                calculatedTruthTable[row][i] = convertSgToInt(variableValue) == 1 ? "1" : "0";
            }
            var valueTokens = new ParsedTokens(parsedVariableValues, parsedVariableTokens.operatorTokens());
            AbstractExpression<Boolean> expression = new LogicalExpressionBuilder().build(valueTokens);
            var logicalExpressionResult = expression.execute();
            calculatedTruthTable[row][column] = logicalExpressionResult ? "1" : "0";
        }
        return calculatedTruthTable;
    }

    private static String[] createHeader(String[] variableNames) {
        var header = new String[variableNames.length+1];
        for (int i = 0; i < variableNames.length; i++) {
            String variableName = variableNames[i];
            header[i] = variableName;
        }
        header[variableNames.length] = "F";
        return header;
    }

    public int convertBlToInt(boolean value){
        if(value){
            return 1;
        }
        return 0;
    }
    public int convertSgToInt(String value){
        if(value == "true"){
            return 1;
        }
        return 0;
    }

    public  int pow(int value,int powValue){
        if(powValue == 0){
            return 1;
        }
        if (powValue == 1) {
            return value;
        } else {
            return value * pow(value, powValue - 1);
        }
    }
    public void welcome(){
        System.out.println("-Hello, my name is Havitch.");
        System.out.println("-I want to learn how to do many things, so please, can you teach me and I would be useful!");
        System.out.println("-Firstly, give me your name - it would be easier to talk.");
    }
    public String getLineInfo(){
        Scanner in = new Scanner(System.in);
        return in.nextLine();
    }

    public int getNumberInfo(){
        Scanner in = new Scanner(System.in);
        return in.nextInt();
    }
    public void education(String name){
        System.out.println("-So "+ name +", I want to get some information about you.");
        System.out.println("-How old are you?");
        int age = getNumberInfo();
        ageComparison(age);
        System.out.println("-What is your favourite color?");
        String favColor = getLineInfo();
        System.out.println("-Ok, now lets get started!");
        System.out.println("I have some commands you can use.");
        System.out.println("You can find all functions by using command (commands).");
    }
    public void commandsList(){
        System.out.println("calc - this command would help you with counting.");
    }
    public void ageComparison(int age){
        if(age < 20){
            lifeInf = youngTalk();
        }
        if((age > 89) && (age <110)){
            lifeInf = luckyTalk();
        }
        if((age > 19) && (age < 90)){
            lifeInf = eldTalk();
        }
        if(age > 109){
            wrongTalk();
        }
    }

    public String youngTalk(){
        System.out.println("-You are young, well what are you going to do in your life?");
        return getLineInfo();
    }
    public String eldTalk(){
        System.out.println("-What are you doing in your life?");
        return getLineInfo();
    }
    public String luckyTalk(){
        System.out.println("-Oh, I am lucky to talk with you!");
        System.out.println("-What have you done during your life?");
        return getLineInfo();
    }
    public void wrongTalk(){
        System.out.println("It's is a bad joke,tell me your real age!");
        ageComparison(getNumberInfo());
    }
    public String inputMathExpression(){
        return getLineInfo();
    }

}