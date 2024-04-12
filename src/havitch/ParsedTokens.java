package havitch;

import java.util.List;

public record ParsedTokens(List<String> operandTokens, List<String> operatorTokens) {

    public List<String> distinctOperands(){
        return operandTokens.stream().distinct().toList();
    }

}
