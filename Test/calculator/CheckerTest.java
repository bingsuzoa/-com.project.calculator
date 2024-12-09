package calculator;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;


class CheckerTest {

    @Test
    public void getTest() {
        String command = "9";
        assertTrue(command.chars().allMatch(Character::isDigit));
    }

    @Test
    public void checkInputStructureTrueTest(){
        Checker checker = new Checker();
        List<Object> checkedCommand;
        try {
            String command = "-1 * -2";
            checkedCommand = checker.checkInputStructure(command);
            for(Object input : checkedCommand){
                System.out.println(input.toString());
            }
        } catch(AssertionError e){
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void checkedTest(){
        List<String> checkCommand = new ArrayList<>();
        List<Object> checkedCommand = new ArrayList<>();
        String command = "-1*-5";

        for(int i = 0; i < command.length(); i++){
            String c = Character.toString(command.charAt(i));
            checkCommand.add(c);
        }

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
    }

    @Test
    public void calculate(){
        KakaoCalculator kakaoCalculator = new KakaoCalculator();
        String command = "2 / 3";
        kakaoCalculator.calculate(command);
    }
}