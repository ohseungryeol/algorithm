import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Point{
        int num; //정점번호
        double x,y;

        public Point(int num, double x, double y) {
            this.num = num;
            this.x = x;
            this.y = y;
        }
    }
    static class Edge implements Comparable<Edge>{
        int start;
        int end;
        double distance;

        public Edge(int start, int end, double distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            if(this.distance-o.distance<0) return -1;
            else return 1;
        }
    }

    //부모노드를 찾는 함수
    public static int getParent(int[] parents, int x){
        if(parents[x]==x) return x;
        return parents[x]=getParent(parents,parents[x]);
    }

    //두 부모노드를 합치는 함수
    public static void unionParent(int[] parents, int a, int b){
        a = getParent(parents, a);
        b = getParent(parents, b);

        //부모노드는 번호가 작은 순으로 합침
        if(a<b) parents[b]=a;
        else parents[a]=b;
    }

    //부모가 같은지 (부모가 같으면 연결된 것임)
    public static boolean sameParent(int[] parents, int a, int b){
        a = getParent(parents,a);
        b = getParent(parents,b);
        if(a==b) return true;

        return false;
    }

    public static double getDistance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p1.x-p2.x,2)+Math.pow(p1.y-p2.y,2));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); //정점 갯수
        int M = Integer.parseInt(st.nextToken()); //주어진 간선 갯수
        int[] parents = new int[N + 1];
        Point[] points = new Point[N+1];

        for (int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points[i]=new Point(i,x,y);
            parents[i]=i;
        }

        //입력에서 주어진 간선은 무조건 포함
        for (int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            unionParent(parents,start,end);

        }

        // 모든 간선을 만들어줌
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i=1; i<N; i++){
            Point s =points[i];
            for (int j=i+1; j<=N; j++){
                Point e=points[j];
                pq.offer(new Edge(s.num,e.num,getDistance(s,e)));
            }
        }
        double answer =0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            //부모가 다르면 간선에 포함 . <-> 모든 노드의 부모가 같아지면 종료
            if(!sameParent(parents, edge.start, edge.end)){
                unionParent(parents, edge.start, edge.end);
                answer+=edge.distance;
            }
        }

        System.out.printf("%.2f",answer);
    }
}
