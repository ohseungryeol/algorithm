import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Pos{
        int x;
        int y;
        int dis;
        boolean destroyed;

        public Pos(int x, int y, int dis, boolean destroyed) {
            this.x = x;
            this.y = y;
            this.dis = dis;
            this.destroyed=destroyed;
        }
    }
    static int R,C;
    static int[][] map;
    static boolean[][][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int Hx,Hy;
    static int Ex,Ey;
    static int answer =0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Hx = Integer.parseInt(st.nextToken());
        Hy = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        Ex = Integer.parseInt(st.nextToken());
        Ey = Integer.parseInt(st.nextToken());

        map = new int[R+1][C+1];
        visited = new boolean[R + 1][C + 1][2];
        for (int i=1; i<=R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(BFS());

    }

    public static int BFS(){
        Queue<Pos> queue = new LinkedList<>();
        queue.offer(new Pos(Hx, Hy, 0,false));
        visited[Hx][Hy][0]=true;

        while(!queue.isEmpty()){
            Pos tmp = queue.poll();
            if(tmp.x==Ex && tmp.y==Ey){
                return tmp.dis;
            }
            for (int i=0; i<4; i++){
                int nx = tmp.x+dx[i];
                int ny = tmp.y+dy[i];
                if(isRange(nx,ny)){
                    if(map[nx][ny]==1) { //벽이고 지팡이 사용 안했으면 갈수있음.
                        if(!tmp.destroyed && !visited[nx][ny][1]){
                            visited[nx][ny][1]=true;
                            queue.offer(new Pos(nx,ny,tmp.dis+1,true));
                        }
                    } else {
                        if(tmp.destroyed && !visited[nx][ny][1]){ //벽을 부순 경우와 안부순 경우 구분
                            visited[nx][ny][1]=true;
                            queue.offer(new Pos(nx,ny,tmp.dis+1,true));
                        } else if(!tmp.destroyed && !visited[nx][ny][0]){
                            visited[nx][ny][0]=true;
                            queue.offer(new Pos(nx,ny,tmp.dis+1,false));
                        }
                    }
                }
            }
        }

        return -1;
    }

    public static boolean isRange(int nx, int ny){
        if(nx>=1 && nx<=R && ny>=1 && ny<=C) return true;
        return false;
    }
}
