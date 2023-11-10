
import java.util.*;
class Solution {
    static class Process implements Comparable<Process>{
        int num;
        int score;

        public Process(int num, int score) {
            this.num = num;
            this.score = score;
        }

        @Override
        public int compareTo(Process o) {
            return o.score-this.score;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Process> queue = new PriorityQueue<>();
        for (int i=0; i< priorities.length; i++){
            queue.add(new Process(i, priorities[i]));
        }
        /*
        1. 실행 대기 큐(Queue)에서 대기중인 프로세스 하나를 꺼냅니다.
        2. 큐에 대기중인 프로세스 중 우선순위가 더 높은 프로세스가 있다면 방금 꺼낸 프로세스를 다시 큐에 넣습니다.
        3. 만약 그런 프로세스가 없다면 방금 꺼낸 프로세스를 실행합니다.
            3.1 한 번 실행한 프로세스는 다시 큐에 넣지 않고 그대로 종료됩니다.
        * */

        while(!queue.isEmpty()){
            for (int i=0; i<priorities.length; i++){
                if(priorities[i]==queue.peek().score){ //
                    answer++;
                    queue.poll();
                    if(i==location) return answer;
                }
            }
        }

        //TODO location에 위치한 프로세스는 몇번째로 실행되는가 ?
        System.out.println(answer);
        return answer;
    }
}