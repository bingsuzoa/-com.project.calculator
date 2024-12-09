package calculator;

public class MyCalculator {
    public double root(String command){
        int x = Integer.parseInt(command);
        return (double) Math.round(Math.sqrt(x) * 100) / 100;
    }

}