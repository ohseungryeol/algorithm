import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int answer =Integer.MAX_VALUE;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static int T;
    static boolean[][][] visited;
    static Queue<Point> queue = new LinkedList<>();
    static class Point{
        int x,y;
        int cnt;
        boolean gram;

        public Point(int x, int y, int cnt,boolean gram) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.gram=gram;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C][2]; //0은 그람 x 1은 그람 o
        for (int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        BFS();
        if(answer<=T) System.out.println(answer);
        else System.out.println("Fail");
    }

    public static void BFS(){
        queue.offer(new Point(0,0,0,false));

        while(!queue.isEmpty()){
            Point tmp = queue.poll();
            if(tmp.x==R-1 && tmp.y==C-1){
                answer = Math.min(answer,tmp.cnt);
            }
            for (int i=0; i<4; i++){
                int nx = tmp.x+dx[i];
                int ny = tmp.y+dy[i];

                if(isRange(nx,ny) && !visited[nx][ny][0]){

                    if(map[nx][ny]==2){
                        visited[nx][ny][0]=true;
                        queue.offer(new Point(nx,ny,tmp.cnt+1,true));
                    } else if(map[nx][ny]==0){
                        visited[nx][ny][0]=true;
                        queue.offer(new Point(nx,ny,tmp.cnt+1,false));
                    }
                }

                if(isRange(nx,ny) && !visited[nx][ny][1] && tmp.gram==true){ //벽일땐 그람이 있어야만
                    visited[nx][ny][1]=true;
                    queue.offer(new Point(nx,ny,tmp.cnt+1,true));
                }
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        if(nx>=0 && nx<R && ny>=0 && ny<C) return true;
        return false;
    }
}
