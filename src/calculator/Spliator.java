package calculator;

import java.util.StringTokenizer;

public class Spliator {
    public int[] organize(OperatorEnum name, String command){
        String arr = command.replaceAll("[^0-9]", " ");
        StringTokenizer st = new StringTokenizer(arr);
        int[] nums = new int[st.countTokens()];
        for(int i = 0; i < nums.length; i++){
            nums[i] = Integer.parseInt(st.nextToken());
        }
        return nums;
    }
}