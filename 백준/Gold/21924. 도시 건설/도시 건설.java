import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N,M;
    static class Edge implements Comparable<Edge>{
        int start,end;
        int distance;

        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance-o.distance;
        }
    }

    public static int getParent(int[] parents, int x){
        if(parents[x]==x) return x;
        return parents[x]=getParent(parents,parents[x]);
    }

    public static void unionParent(int[] parents, int a, int b){
        a = getParent(parents,a);
        b = getParent(parents,b);

        if(a<b) parents[b]=a;
        else parents[a]=b;
    }

    public static boolean sameParent(int[] parents, int a, int b){
        a = getParent(parents,a);
        b = getParent(parents,b);

        if(a==b) return true;

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] parents = new int[N + 1];

        for (int i=1; i<=N; i++){
            parents[i]=i;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        long totalDistance =0;
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            totalDistance+=distance;
            pq.offer(new Edge(start,end,distance));
        }

        int cnt =0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(!sameParent(parents,edge.start,edge.end)){
                unionParent(parents, edge.start, edge.end);
                totalDistance-=edge.distance;
                cnt++;
            }
        }
        if(cnt<N-1) System.out.println(-1);
        else System.out.println(totalDistance);

    }
}
