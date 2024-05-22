
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Stack;

public class Solution {
    public String solution(String number, int k) {
        String answer = "";
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        int remain = k;
        boolean flag=false;
        int idx = 0;
        for (int i = 0; i < number.length(); i++) {
            int num = Integer.parseInt(String.valueOf(number.charAt(i)));
            //stack.push(num);

            if (stack.isEmpty()) {
                stack.push(num);
                continue;
            }


            if (remain == 0) {
                idx = i;
                break;
            }
            if (stack.peek() < num) {
                while (!stack.isEmpty()) {
                    if (stack.peek() < num) {
                        stack.pop();
                        remain--;
                        if (remain == 0) {
                            idx = i + 1;
                            break;
                        }
                    } else {
                        break;
                    }
                }
                stack.push(num);
            } else {
                stack.push(num);
            }
        }


        if(stack.size()==number.length()){
            for (int i=0; i< number.length()-k; i++){
                //answer += number.charAt(i);
                sb.append(number.charAt(i));
            }
           // System.out.println(answer);
            return sb.toString();

        }

        for (int i = 0; i < stack.size(); i++) {
            // answer += stack.get(i);
            sb.append(stack.get(i));
        }


        for (int i = idx; i < number.length(); i++) {
            // answer += number.charAt(i);
            sb.append(number.charAt(i));
        }

        
        return sb.toString();
    }

  
}
