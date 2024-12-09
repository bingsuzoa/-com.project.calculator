package calculator;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.IntBinaryOperator;

public enum OperatorEnum {
    ADD("+", (x , y) -> x + y),
    MINUS("-", (x , y) -> x - y),
    MULTIPLY("*", (x , y) -> x * y),
    DIVIDE("/", (x , y) -> {
        if(y == 0) throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        return x / y;
    }),
    SQUARE("^", (x , y) -> (int)Math.pow(x,y));

    private final String sign;
    private final IntBinaryOperator expression;

    private static final Map<String, OperatorEnum> operatorMap;
    static{
        Map<String, OperatorEnum> temp = new HashMap<>();
        temp.put("+", ADD);
        temp.put("-", MINUS);
        temp.put("*", MULTIPLY);
        temp.put("/", DIVIDE);
        temp.put("^", SQUARE);
        operatorMap = Collections.unmodifiableMap(temp);
    }


    OperatorEnum(String sign, IntBinaryOperator expression){
        this.sign = sign;
        this.expression = expression;
    }

    public String getSign(){
        return sign;
    }

    public static OperatorEnum getSymbol(final String sign){
        return operatorMap.get(sign);
    }

    public int apply(int x, int y){
        return expression.applyAsInt(x, y);
    }

}