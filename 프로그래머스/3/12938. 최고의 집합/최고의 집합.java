
import java.util.Arrays;

public class Solution {
    public int[] solution(int n, int s) {
        //500 9
        if(s/n==0){
            return new int[]{-1};
        }
        int[] answer = new int[n];
        int diff = s%n;
        if(s%n==0){
            for (int i=0; i<n; i++){
                answer[i] = s/n;
            }
        } else {
            for (int i=0; i<n-diff; i++){
                answer[i] = s/n;
            }
            for (int i=n-diff; i<n; i++){
                answer[i]=s/n+1;
            }
        }
        return answer;
    }

   
}
