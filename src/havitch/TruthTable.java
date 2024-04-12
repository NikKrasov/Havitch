package havitch;

import java.util.List;

public class TruthTable {
    public  String[][] makeTable(ParsedTokens expressionTokens) {
        List<String> distinctVariables = expressionTokens.distinctOperands();
        var variablesCount = distinctVariables.size();
        int rowsCount = pow(2, variablesCount)+1;
        String[][] table = new String[rowsCount][variablesCount];
        for (int row_i = 1; row_i < rowsCount; row_i++) {
            for (int column_i = 0; column_i < variablesCount; column_i++) {
                table[row_i][column_i] = calculateCellValue(row_i-1, column_i,variablesCount);
            }
        }
        for(int i = 0;i < variablesCount; i++){
            table[0][i] =distinctVariables.get(i);
        }
        return table;
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
    private String calculateCellValue(int rowI, int columnI,int columnsCount) {
        columnsCount = columnsCount - 1;
        int termValue = pow(2,(columnsCount - columnI));
        if(rowI == 0){
            return "false";
        }
        if ((rowI / termValue) % 2 == 1){
            return "true";
        }
        if ((rowI / termValue) % 2 == 0){
            return "false";
        }
        return "false";
    }
}

