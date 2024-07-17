
import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        // 다리에는 최대 length개 트럭이 올라갈 수 있음
        // 다리에는 weight 이하의 무게까지만
        // 정해진 순으로 모든 트럭이 다리를 건너는데 몇초?
        Queue<Integer> queue = new LinkedList<>();

        int time =0;
        int cur_weight=0;
        int i=0;
        while (true){
            // 1. 다리 허용갯수는 초과하지 않는가?
            // 2. 올리면 무게가 초과하지는 않는가?
            if(i==truck_weights.length && queue.size()==0) break;
            if(queue.size()+1<=bridge_length){ //갯수 허용

                if(i<truck_weights.length && cur_weight+truck_weights[i]<=weight){
                    cur_weight+=truck_weights[i];
                    queue.offer(truck_weights[i++]);
                    time++;
                } else { //무게 초과로 못넣는 경우에는 0을 추가
                    while(queue.size()<=bridge_length-1) {
                        queue.offer(0);
                        time++;
                    }
                }
            } else if (queue.size()==bridge_length){
                while(!queue.isEmpty()){
                    cur_weight -= queue.poll();
                }
            }
        }

        System.out.println(time);
        return time;
    }

    
}
