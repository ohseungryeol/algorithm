import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static int answer=0;

    static class Point{
        int x,y,nextX,nextY;

        public Point(int x, int y, int nextX, int nextY) {
            this.x = x;
            this.y = y;
            this.nextX = nextX;
            this.nextY = nextY;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        if(map[N-1][N-1]==1){
            System.out.println(0);
            return;
        }
        BFS();
        System.out.println(answer);

    }

    public static void BFS(){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, 0, 1));

        while(!queue.isEmpty()){
            Point cur = queue.poll();
            int cx = cur.x;
            int cy = cur.y;
            int nx = cur.nextX;
            int ny = cur.nextY;
            if(nx==N-1 && ny==N-1){
                answer++;
                continue;
            }
            //가로
            if(cx==nx && cy+1==ny){
                //직진
                if(isRange(nx,ny+1)) queue.offer(new Point(nx,ny,nx,ny+1));
                //대각선
                if(isRange(nx+1,ny+1) && map[nx+1][ny]==0 && map[nx][ny+1]==0) queue.offer(new Point(nx,ny,nx+1,ny+1));
            }
            //세로
            if(cx+1==nx && cy==ny){
                //직진
                if(isRange(nx+1,ny)) queue.offer(new Point(nx,ny,nx+1,ny));
                //대각선
                if(isRange(nx+1,ny+1) && map[nx+1][ny]==0 && map[nx][ny+1]==0) queue.offer(new Point(nx, ny, nx + 1, ny + 1));
            }
            //대각선
            if(cx+1==nx && cy+1==ny){
                //오른 대각선
                if(isRange(nx,ny+1)) queue.offer(new Point(nx,ny,nx,ny+1));
                //아래 대각선
                if(isRange(nx+1,ny)) queue.offer(new Point(nx, ny, nx + 1, ny));

                //직진
                if(isRange(nx+1,ny+1) && map[nx+1][ny]==0 && map[nx][ny+1]==0) queue.offer(new Point(nx, ny, nx + 1, ny + 1));
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        return (nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]==0);
    }
}
