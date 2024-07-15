import java.util.Collections;
import java.util.PriorityQueue;
class Solution {
    public long solution(int n, int[] works) {
       long answer = 0;
       long total=0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < works.length; i++){
            pq.offer(works[i]);
            total+=works[i];
        } 
        
        if(total<n) return 0;
        
        // 2 2 2 
        while (n-->0){
            int num = pq.poll();
            pq.offer(--num);
        }
        
        while(!pq.isEmpty()){
            int num = pq.poll();
            answer+=num*num;
        }
        return answer;
    }
}