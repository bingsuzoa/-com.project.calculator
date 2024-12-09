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



    //---------------------------------
    //연산포함

    @Deprecated
    public boolean checkValidSign(String command) {
        for(OperatorEnum ope : OperatorEnum.values()) {
            if (command.contains(ope.getSign())) return true;
            break;
        }
        throw new IllegalArgumentException("유효하지않은 연산자가 포함되어있습니다.");
    }
    @Deprecated
    public Queue<Object> parseCalculator(String command){
        command = command.replace(" ", "");
        Queue<Object> numberQue = new LinkedList<>();
        int maker;
        int index = 0;

        for(int i = 1; i < command.length(); i++){
            String value = Character.toString(command.charAt(i));
            for(OperatorEnum ope : OperatorEnum.values()){
                if(value.equals("-") && !Character.toString(command.charAt(i-1)).matches("^[0-9.]+$")){
                    continue;
                }
                String enumSign = ope.getSign();
                if(value.equals(enumSign)){
                    maker = Integer.parseInt(command.substring(index, i));
                    numberQue.add(maker);
                    numberQue.add(enumSign);
                    index = i+1;
                    break;
                }
            }
        }
        maker = Integer.parseInt(command.substring(index));
        numberQue.add(maker);
        return numberQue;
    }
}
