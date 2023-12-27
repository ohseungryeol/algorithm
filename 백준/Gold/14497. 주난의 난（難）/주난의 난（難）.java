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
    static int x1,y1,x2,y2;
    static char[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[][] dist;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        dist = new int[R][C];
        st = new StringTokenizer(br.readLine());

        //주난 위치
        x1 = Integer.parseInt(st.nextToken())-1;
        y1 = Integer.parseInt(st.nextToken())-1;
        //범인 위치
        x2 = Integer.parseInt(st.nextToken())-1;
        y2 = Integer.parseInt(st.nextToken())-1;
        visited[x1][y1]=true;
        for (int i=0; i<R; i++){
            String tmp = br.readLine();
            for (int j=0; j<C; j++){
                map[i][j] = tmp.charAt(j);
            }
        }
        BFS();
        System.out.println(answer);
    }

    public static void BFS(){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x1,y1,0});

        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int cnt = tmp[2];
           // System.out.printf("(%d,%d,%d)\n",x,y,cnt);
            if (map[x][y]=='#'){
                answer = cnt+1;
                //return;
            }
            for (int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(isRange(nx,ny)){
                    if( map[nx][ny]=='0' || map[nx][ny]=='#'){
                        if(visited[nx][ny] && cnt<dist[nx][ny]){ //이미 방문을 했지만 최소 거리이므로 갱신
                            dist[nx][ny]=cnt;
                            queue.offer(new int[]{nx,ny,cnt});
                        } else if (!visited[nx][ny]){
                            visited[nx][ny]=true;
                            dist[nx][ny]=cnt;
                            queue.offer(new int[]{nx,ny,cnt});
                        }

                    }
                    if( map[nx][ny]=='1'){
                        if(visited[nx][ny] && cnt+1<dist[nx][ny]){ //이미 방문을 했지만 최소 거리이므로 갱신
                            dist[nx][ny]=cnt+1;
                            queue.offer(new int[]{nx,ny,cnt+1});
                        } else if (!visited[nx][ny]){
                            visited[nx][ny]=true;
                            dist[nx][ny]=cnt+1;
                            queue.offer(new int[]{nx,ny,cnt+1});
                        }
                    }
                }
            }
        }
    }

    public static boolean isRange(int nx , int ny){
        if(nx>=0 && nx<R && ny>=0 && ny<C) return true;
        return false;
    }
}
