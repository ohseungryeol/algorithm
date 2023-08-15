import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] map;
    static int[][] dis;
    static boolean[][][] visited;
    static Queue<Point> queue = new LinkedList<>();

    static class Point{
        int x,y;
        boolean wall;

        public Point(int x, int y, boolean wall) {
            this.x = x;
            this.y = y;
            this.wall = wall; //1이면 부순거 0이면 안부순거
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        dis = new int[R][C];

        visited = new boolean[R][C][2];
        for (int i=0; i<R; i++){
            String tmp = br.readLine();
            for (int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(String.valueOf(tmp.charAt(j)));
                queue.offer(new Point(0, 0, false));
            }
        }

        BFS();
        
        if(R==1 && C==1){
            if(map[R-1][C-1]==0) System.out.println(1);
            else System.out.println(-1);

            return;
        }
        if(dis[R-1][C-1]==0){
            System.out.println(-1);
        } else{
            System.out.println(dis[R-1][C-1]+1);
        }

    }

    public static void BFS(){

        /*벽을 부순 경우와 벽을 안부순 경우중 먼저 목적지에 도착하는 경우가 최단거리이다.*/
        while(!queue.isEmpty()){
            Point current = queue.poll();

            if(current.x==R-1 && current.y==C-1) return;
            for (int i=0; i<4; i++){
                int nx = current.x+dx[i];
                int ny = current.y+dy[i];

                if(nx>=0 && nx<R && ny>=0 && ny<C){
                    if(map[nx][ny]==0){ //이동 가능한 경우
                        if(!current.wall){ //벽을 부수지 않은 상태
                            if(!visited[nx][ny][0]) {
                                visited[nx][ny][0] = true;
                                dis[nx][ny] = dis[current.x][current.y] + 1;
                                queue.offer(new Point(nx,ny,false));
                            }
                        } else { //부순상태
                            if(!visited[nx][ny][1]) {
                                visited[nx][ny][1] = true;
                                dis[nx][ny] = dis[current.x][current.y] + 1;
                                queue.offer(new Point(nx,ny,true));
                            }
                        }
                    } else if (map[nx][ny]==1){ //벽인 경우
                        //이미 벽을 부순 케이스는 또 부쉴 수 없음.
                        if(current.wall==true) continue;
                        if(!visited[nx][ny][0]){
                            visited[nx][ny][1]=true;
                            dis[nx][ny]=dis[current.x][current.y]+1;
                            queue.offer(new Point(nx,ny,true));

                        }
                    }
                }
            }
        }
    }
}
