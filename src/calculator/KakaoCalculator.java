package calculator;

import java.util.ArrayList;
import java.util.List;

public class KakaoCalculator {
    private final Checker checker = new Checker();
    private final List<String> list = new ArrayList<>();
    private final MyCalculator myCal = new MyCalculator();

    public void calculate(String command) {
        command = command.replace(" ", "");
        try{
            List<Object> checkedCommand = checker.checkInputStructure(command);
            if(checkedCommand.size() == 1) {
                double result = myCal.root(command);
                printRoot(command, result);
            } else {
                int x = (int)checkedCommand.getFirst();
                String operator = (String)checkedCommand.get(1);
                int y = (int)checkedCommand.getLast();
                OperatorEnum operatorEnum = OperatorEnum.getSymbol(operator);
                int result = operatorEnum.apply(x,y);
                printResult(x, operatorEnum.getSign(), y, result);
            }

        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch(ArithmeticException e){
            System.out.println("0으로 나눌 수 없습니다.");
        }
    }
    public List<String> getList(){
        return list;
    }

    public void printRoot(String command, double result){
        System.out.println("카카오 계산 결과 : " + result);
        list.add("root " + command + " = : " + result);
    }

    public void printResult(int x, String name, int y, int result){
        System.out.println("카카오 계산 결과 : " + x + " " + name + " " + y + " = " + result);
        list.add(x + " " + name + " " + y + " = " + result);
    }
}