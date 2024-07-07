
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        int[] distance = new int[n + 1];
        int max = Integer.MAX_VALUE;

        Arrays.fill(distance, max);

        for (int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }
        for (int[] road:roads){
            int cur = road[0];
            int next = road[1];

            graph.get(cur).add(next);
            graph.get(next).add(cur);
        }
        //각 정점에서 가는 최단거리.

        for (int i=0; i<sources.length; i++){
            int dis = Djkstras(destination,sources[i],distance);
            
            if(dis==max) answer[i]=-1;
            else answer[i]=dis;
        }

        System.out.println(Arrays.toString(answer));

        return answer;
    }

    public static int Djkstras(int start, int end, int[] distance ){
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        distance[start]=0;

        //몇번의 간선을 지나는가.
        while(!queue.isEmpty()){
            int cv = queue.poll();
         
            for (int nv:graph.get(cv)){
                if(distance[nv]>distance[cv]+1){
                    distance[nv]=distance[cv]+1;
                    queue.offer(nv);
                }
                
            }
        }

        return distance[end];
    }

}
