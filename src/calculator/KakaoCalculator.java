package calculator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class KakaoCalculator {
    private final Spliator spliator = new Spliator();
    private final Checker checker = new Checker();
    private final List<String> list = new ArrayList<>();
    private final MyCalculator myCal = new MyCalculator();

    public void calculate(String command) {
        command = command.replace(" ", "");
        Queue<Object> numberQue = new LinkedList<>();

        try{
//            int checkedInput = checker.checkInputStructure(command);
//            switch (checkedInput){
//                case 1 : myCal.root(command);
//                case 2 : calculate(command);
//            }
            if(checker.checkValidSign(command)) numberQue = checker.parseCalculator(command);

            int result = (int)numberQue.peek();
            while(!numberQue.isEmpty()){
                int num1 = result;
                String temp = (String)numberQue.peek();
                OperatorEnum operator = OperatorEnum.getSymbol(temp);
                int num2 = (int)numberQue.peek();
                result = operator.apply(num1, num2);
                printResult(num1, temp, num2, result);
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

    public String printResult(int x, String name, int y, int result){
        System.out.println("카카오 계산 결과 : " + x + " " + name + " " + y + " = " + result);
        return x + " " + name + " " + y + " = " + result;
    }
}