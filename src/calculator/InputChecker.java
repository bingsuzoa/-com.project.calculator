package calculator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InputChecker {

    public Map<String, Object> checkInput(String input){
        Map<String, Object> checkedInputMap = new HashMap<>();

        String operatorString = parseInput(input);
        int index = 0;
        for(int i = 1; i < input.length(); i++) {
            String charOfInput = Character.toString(input.charAt(i));
            if(findIndexOfOperator(charOfInput, operatorString)) {
                index = i;
                break;
            }
        }
        checkedInputMap.put("operator", operatorString);
        checkedInputMap.put("index", index);

        return checkedInputMap;
    }

    public String parseInput(String input) {
        List<String> inputCharList = new ArrayList<>();
        List<String> operatorOfInput;

        //1.입력값이 숫자로만 구성된 경우
        if(input.matches("^[0-9 .]*$")) return input;

        //2.입력값에 문자포함된 경우
        //2-1) input을 char단위로 리스트에 담기
        for (int i = 1; i < input.length(); i++) {
            inputCharList.add(Character.toString(input.charAt(i)));
        }

        //2-2) 연산자 추출
        operatorOfInput = inputCharList.stream()
                .filter(charOfInput -> OperatorEnum.getEnumFromSign(charOfInput) != null)
                .toList();

        //2-3) operatorOfInput이 비어있는 경우 1. 음수 2. 유효하지 않은 연산자
        //1.음수 체크
        if(operatorOfInput.isEmpty() && input.charAt(0) == '-') isNegativeNumber(input);
        //2.유효하지 않은 연산자 체크
        else if(operatorOfInput.isEmpty()) throw new IllegalArgumentException(input + " : 유효하지 않은 연산자가 포함되어있습니다.");

        return operatorOfInput.getFirst();
    }

    public void isNegativeNumber(String input) {
        double onlyNegativeNumber = Double.parseDouble(input);
        if(onlyNegativeNumber < 0) throw new IllegalArgumentException(input + " : 입력한 값이 음수입니다.");
    }

    public boolean findIndexOfOperator(String input, String operator) {
        if(input.equals(operator)) return true;
        else return false;
    }
}
