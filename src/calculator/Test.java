package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws IOException {
        Checker checker = new Checker();
        KakaoCalculator cal = new KakaoCalculator();
        MyCalculator myCalculator = new MyCalculator();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            int N = Integer.parseInt(br.readLine());
            switch(N){
                case 1 :
                    System.out.println("숫자 연산자 숫자 형식 또는 숫자 형식으로 입력해주세요 예) 5 + 4 또는 5");
                    String command = br.readLine();
                    cal.calculate(command);
                    break;
                case 2 :
                    System.out.println("히스토리를 보여드리겠습니다.");
                    for(String result : cal.getList()){
                        System.out.println(result);
                    }
                    break;
                case 3 :
                    System.out.println("프로그램을 종료합니다.");
                    return;
                default :
                    System.out.println("잘못된 입력 형식입니다. 다시 입력하세요.");
                    break;
            }
        }
    }

}