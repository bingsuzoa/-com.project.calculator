package calculator;

import java.util.*;

public class Checker {

    public List<Object> checkInputStructure(String command){
        command = command.replace(" ", "");
        List<String> checkCommand = new ArrayList<>();
        List<Object> checkedCommand = new ArrayList<>();

        for(int i = 0; i < command.length(); i++){
            String c = Character.toString(command.charAt(i));
            checkCommand.add(c);
        }
        if(checkCommand.size() == 1){
            String c = checkCommand.getFirst();
            if(c.chars().allMatch(Character::isDigit)) {
                checkedCommand.add(Integer.parseInt(c));
            } else {
                throw new IllegalArgumentException(c + " 는 유효하지 않은 입력입니다. 입력을 다시 확인해주세요.");
            }
        }
        else {
            boolean complete = false;
            for (int i = 1; i < checkCommand.size(); i++) {
                if (complete) break;
                for (OperatorEnum ope : OperatorEnum.values()) {
                    String c = checkCommand.get(i);
                    if (c.equals(ope.getSign())) {
                        int x = Integer.parseInt(command.substring(0, i));
                        checkedCommand.add(x);
                        checkedCommand.add(ope.getSign());
                        int y = Integer.parseInt(command.substring(i+1));
                        checkedCommand.add(y);
                        complete = true;
                        break;
                    }
                }
                if (i == checkCommand.size() - 1) {
                    try {
                        int x = Integer.parseInt(command);
                        if(x < 0) throw new IllegalArgumentException(x + " : 음수만 입력하셨습니다. 연산이 불가합니다.");
                        if(x > 0) checkedCommand.add(x);
                    } catch (NumberFormatException e) {
                        throw new NumberFormatException(command + " : 유효하지 않은 연산자 입니다.");
                    }
                }
            }
        }
        return checkedCommand;
    }
}
