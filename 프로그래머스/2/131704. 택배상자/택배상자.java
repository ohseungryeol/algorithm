
import java.util.Stack;

public class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int idx =0;
        for (int i=1; i<=order.length; i++){
            boolean flag= false;
            if(i==order[idx]){
                answer++;
                idx++;

                while(!stack.isEmpty()){
                    if(stack.peek()==order[idx]){
                        idx++;
                        answer++;
                        stack.pop();
                    } else {
                        break;
                    }
                }
                flag = true;
                continue;
            }
           // if(!flag && i>order[idx]) break;
            stack.add(i);
        }
        System.out.println(answer);
        return answer;
    }
    
}
