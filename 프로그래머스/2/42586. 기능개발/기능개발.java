import java.util.*;
class Solution {
    public ArrayList<Integer> solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> answer = new ArrayList<>();
        int len = speeds.length;
        int[] remainsTime = new int[len];
        for (int i=0; i<len; i++){
            int diff = 100-progresses[i];
            int remains=0;
            if(diff%speeds[i]==0){
                remains = diff/speeds[i];
            } else {
                remains = diff/speeds[i]+1;
            }
            
            remainsTime[i]=remains;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(remainsTime[0]);
        
        for (int i=1; i<len; i++){
            if(remainsTime[i]<=queue.peek()){
                queue.offer(remainsTime[i]);
            } else {
                answer.add(queue.size());
                queue.clear();
                queue.offer(remainsTime[i]);
            }
        }
        
        if(!queue.isEmpty()) answer.add(queue.size());
        
        
        return answer;
    }
}