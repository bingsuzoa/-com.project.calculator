package calculator;

public class MyCalculator {
    public void root(String command){
        int x = Integer.parseInt(command);
        int result = (int) Math.sqrt(x);
        System.out.println("카카오 계산 결과 : " + result);
    }

}