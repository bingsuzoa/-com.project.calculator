package calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Input input = new Input();

        while(true){
            int N = Integer.parseInt(br.readLine());
            int result = input.processInput(N);
            if(result== 3) break;
        }
    }

}