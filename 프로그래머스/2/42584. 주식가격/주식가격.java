import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        
        Queue<int[]> queue = new LinkedList<>();
        int len = prices.length;
        int[] answer = new int[len];
        
        for (int i=0; i<len; i++) queue.offer(new int[]{prices[i],i});
        
        int j=0;
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int idx =tmp[1];
            int curPrice = tmp[0];
            int cnt=0;
            for (int i=idx+1; i<len; i++){
                if(curPrice<=prices[i]){
                    cnt++;
                } else {
                    cnt++;
                    break;
                }
            }
            
            
            answer[j++]=cnt;
        }
        
        return answer;
        
    }
}