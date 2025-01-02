package calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KakaoCalculator {
    private final InputChecker checker = new InputChecker();
    private final List<String> list = new ArrayList<>();

    public double calculate(String input) {
        input = input.replace(" ", "");
        double result = 0;
        try{
            Map<String, Object> checkedInputMap = checker.checkInput(input);
            String operatorString = (String)checkedInputMap.get("operator");
            int index = (Integer)checkedInputMap.get("index");

            if(operatorString.matches("^[0-9 .]*$")) {
                double x = Double.parseDouble(operatorString);
                result =  (double) Math.round(Math.sqrt(x) * 100) / 100;
                printSquareRoot(input, result);
            } else {
                double x = Double.parseDouble(input.substring(0, index));
                double y = Double.parseDouble(input.substring(index + 1));
                OperatorEnum operator = OperatorEnum.getEnumFromSign(operatorString);
                result = operator.apply(x,y);
                printResult(x, operator.getSign(), y, result);
            }
        } catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        } catch(ArithmeticException e){
            System.out.println("0으로 나눌 수 없습니다.");
        }
        return result;
    }

    public List<String> getList(){
        for(String result : list) System.out.println(result);
        return list;
    }

    public String printSquareRoot(String command, double result){
        System.out.println("카카오 계산 결과 : " + result);
        list.add("제곱근 " + command + " = : " + result);
        return "카카오 계산 결과 : " + result;
    }

    public String printResult(double x, String name, double y, double result){
        System.out.println("카카오 계산 결과 : " + x + " " + name + " " + y + " = " + result);
        list.add(x + " " + name + " " + y + " = " + result);
        return "카카오 계산 결과 : " + x + " " + name + " " + y + " = " + result;
    }
}