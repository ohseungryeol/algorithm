import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
    static boolean[] visited;
    // 각 정점으로 가는 최소 비용을 저장한 배열
    static int[] dist;
    static class Edge implements Comparable<Edge>{
        int v;
        int cost;

        public Edge(int v, int cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        dist = new int[N + 1];
        visited = new boolean[N + 1];

        Arrays.fill(dist,Integer.MAX_VALUE);
        for (int i=0; i<=N; i++){
            graph.add(new ArrayList<Edge>());
        }

        StringTokenizer st;
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(s).add(new Edge(e, c));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Dijkstra(start);
        System.out.println(dist[end]);

    }

    public static void Dijkstra(int start){
        dist[start]=0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));

        while(!pq.isEmpty()){
            Edge cur = pq.poll();
            int cv = cur.v;

            if(dist[cv]<cur.cost) continue;
            for (Edge next:graph.get(cv)){
                int nv = next.v;
                int cost =next.cost;

                if(dist[cv]+cost<dist[nv]){
                    dist[nv]=dist[cv]+cost;
                    pq.offer(next);
                }

               
            }
        }

    }
}
