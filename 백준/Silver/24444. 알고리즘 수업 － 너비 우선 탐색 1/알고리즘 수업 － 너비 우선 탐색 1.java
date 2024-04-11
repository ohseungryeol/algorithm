import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int count =1;
    static Queue<Integer> queue = new LinkedList<>();
    static ArrayList<ArrayList<Integer>> graph= new ArrayList<>();
    public static void main(String[] args) throws IOException {

        //인접정점은 오름차순으로 방문
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());// 정점의 수 1~N
        int M = Integer.parseInt(st.nextToken()); // 간선의 수
        int R = Integer.parseInt(st.nextToken());


        visited = new boolean[N + 1];
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        boolean flag = false;
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(s).add(e);
            graph.get(e).add(s);
        }

        for (int i=1; i<=N; i++){
            Collections.sort(graph.get(i));
        }
        int[] answer = new int[N+1];
        BFS(R,answer);

        for (int i=1; i<=N; i++){
            if(!visited[i]) sb.append(0).append('\n');
        }


        for (int i=1; i<=N; i++){
            System.out.println(answer[i]);
        }


    }

    public static void BFS(int start, int[] answer){

        queue.offer(start);
        visited[start]=true;

        while(!queue.isEmpty()){
            int cur = queue.poll();
            answer[cur]=count++;
            for(Integer next: graph.get(cur)){
                // 2랑 4
                if(!visited[next]){
                    visited[next]=true;
                    queue.offer(next);
                }
            }
        }
    }

}
