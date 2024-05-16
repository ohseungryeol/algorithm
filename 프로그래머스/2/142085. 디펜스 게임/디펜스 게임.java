
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.PriorityQueue;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        int round =0;
        int remain=n;

        for (int i=0; i<enemy.length; i++){

            //지나온 라운드에서 가장 큰 값만 무적권을 쓰자
            remain -= enemy[i];
            pq.offer(enemy[i]);
            if(remain<0){
                if(k>0){
                    k--;
                    remain+=pq.poll();
                    round++;
                } else {
                    break;
                }
            } else{
                round++;
            }


        }

        System.out.println(round);

        return round;
    }

   
}
