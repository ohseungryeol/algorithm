
import java.util.*;
class Solution {

    public int solution(int n, int k, int[] enemy) {
        int answer = 0;

        // 최대한 많은 라운드를 진행
        // 순서는 보장해야함

        // 4 2 4 5 3 3 1
        // 무적권으로 많은 애들을 쳐내는 것이 중요
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        // n=5 k=1
        for (int i=0; i<enemy.length; i++){
            pq.offer(enemy[i]);
            n-=enemy[i];
            
            if(n<0){
                if(k>0){
                    n+=pq.poll();
                    k--;
                    answer++;
                    continue;
                } else {
                    break;
                } 
            }
            answer++;
        }
        return answer;
    }

   
}


