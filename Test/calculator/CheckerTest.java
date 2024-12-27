package calculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class CheckerTest {
    KakaoCalculator calculator = new KakaoCalculator();
    InputChecker checker = new InputChecker();
    Input input = new Input();

    //////////////////해피 테스트
    @DisplayName("덧셈 테스트")
    @ParameterizedTest
    @CsvSource({"5 + 3, 8", "-5 + 3, -2", "-5 + -3, -8", " 0.5 + 0.5, 1"})
    public void add(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }

    @DisplayName("뺄셈 테스트")
    @ParameterizedTest
    @CsvSource({"5 - 3, 2", "-5 - 3, -8", "-5 - -3, -2", " 5 - 0, 5"})
    public void minus(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }

    @DisplayName("곱셈 테스트")
    @ParameterizedTest
    @CsvSource({"5 * 3, 15", "-5 * 3, -15", "-0.5 * -0.3, 0.15", " 5 * 0, 0"})
    public void multiply(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }

    @DisplayName("나눗셈 테스트")
    @ParameterizedTest
    @CsvSource({"5 / 3, 1.67", "-5 / 3, -1.67", "-5 / -3, 1.67"})
    public void divide(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }

    @DisplayName("제곱근 테스트 : 양수(정수,실수)")
    @ParameterizedTest
    @CsvSource({"25 , 5", "0.25 , 0.5"})
    public void root(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }

    @DisplayName("제곱 테스트")
    @ParameterizedTest
    @CsvSource({"5 ^ 2, 25", "-5 ^ 3, -125", "-0.5 ^ 2, 0.25", " 5 ^ 0, 1", "5 ^ -1, 0.2"})
    public void square(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }

    @DisplayName("메모리 저장 및 출력 테스트")
    @Test
    public void continousCalculation(){
        String[] commandArr = {"5 ^ 2", "5 -2"};
        for(String command : commandArr){
            calculator.calculate(command);
        }
        assertEquals("[5.0 ^ 2.0 = 25.0, 5.0 - 2.0 = 3.0]", calculator.getList().toString());
    }


    @ParameterizedTest
    @DisplayName("명령 입출력 테스트: 1-1 / 2-2 / 3-3")
    @ValueSource(ints = {2,3,4})
    public void swtichTest(int command) throws IOException {
        int result = input.processInput(command);
        assertEquals(command, result);
    }

    ///////////////////////////////////에러 테스트
    @ParameterizedTest
    @DisplayName("0으로 나누었을 때 : throw ArithmeticException")

    @CsvSource(value = {"5,0", "0, -5"})
    public void divideZeroException(double x, double y){;
        OperatorEnum operator = OperatorEnum.getEnumFromSign("/");
        assertThrows(ArithmeticException.class, () -> operator.apply(x,y));
    }

    @ParameterizedTest
    @DisplayName("유효하지 않은 연산자 입력했을 때 : throw IllegalArgumentException")
    @ValueSource(strings = {"5 # 3, 5 ? 3, 5 @ 2"})
    public void invalidOperatorException(String command){
        assertThrows(IllegalArgumentException.class, () -> checker.checkInput(command));
    }

    @DisplayName("음수만 입력했을 때 : IllegalArgumentException")
    @Test
    public void negativeException(){
        String command = "-1.5";
        assertThrows(IllegalArgumentException.class, () -> checker.checkInput(command));

    }

    ///////////////////////////경계테스트
    @ParameterizedTest
    @DisplayName("초과되는 범위의 수 입력 했을 때 : Infinity 출력 / 0 출력")
    @ValueSource(strings = {"25 ^ 24424803958038539852852098850","25 ^ -2442480395803853985205093850"})
    public void outOfRangeException(String command){
        calculator.calculate(command);
    }

    @ParameterizedTest
    @DisplayName("소수점 연산 테스트 : 나눗셈, 제곱근과 같이 나누어 떨어지지 않는 경우 소수 셋째자리에서 반올림")
    @CsvSource({"10 / 7, 1.43", "10 , 3.16"})
    public void decimalTest(String command, double expectedResult){
        double actualResult = calculator.calculate(command);
        assertEquals(expectedResult,actualResult);
    }
}