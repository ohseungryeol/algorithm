import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int[][] map;
    static int answer=0;
    static boolean[][] visited;
    static int[] dx ={-1,0,1,0};
    static int[] dy ={0,-1,0,1};
    static Queue<Point> queue = new LinkedList<>();
    static class Point{
        int x,y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C];

        /*벽(1)을 무조건 3개씩 세워서 안전영역(0)의 갯수의 최댓값을 구한다.*/
        for (int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0);
        System.out.println(answer);
    }

    public static void DFS(int N){
        if(N==3){
            BFS();
            return;
        }
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if(map[i][j]==0){
                   map[i][j]=1;
                   DFS(N+1);
                   map[i][j]=0;
                }
            }
        }
    }

    public static void BFS(){
        int[][] copyMap = new int[R][C];

        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                copyMap[i][j]=map[i][j];
                if(copyMap[i][j]==2){
                    queue.offer(new Point(i,j));
                }
            }
        }

        while(!queue.isEmpty()){
            Point tmp = queue.poll();
            for (int i=0; i<4; i++){
                int nx = tmp.x+dx[i];
                int ny = tmp.y+dy[i];

                if(nx>=0 && nx<R && ny>=0 && ny<C && copyMap[nx][ny]==0){
                    copyMap[nx][ny]=2;
                    queue.offer(new Point(nx, ny));
                }
            }
        }
        int max = Integer.MIN_VALUE;
        int cnt=0;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if(copyMap[i][j]==0){
                    cnt++;
                }
            }
        }

        answer = Math.max(answer,cnt);
       // System.out.println("answer="+answer);
    }
}
