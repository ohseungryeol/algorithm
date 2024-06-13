import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,C,K;
    static char[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];

        for (int i=0; i<R; i++){
            String tmp = br.readLine();
            for (int j=0; j<C; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        visited[R-1][0]=true;
        DFS(R - 1, 0, 1);

        System.out.println(answer);

    }
    //출발 = R-1,0
    //도착 = 0,C-1;
    public static void DFS(int x, int y, int cnt){
        if(x==0 && y==C-1){
            if(cnt==K) answer++;
            return;
        }

        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isRange(nx,ny) && map[nx][ny]=='.' && !visited[nx][ny]){
                visited[nx][ny]=true;
                DFS(nx,ny,cnt+1);
                visited[nx][ny]=false;
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        return (nx >= 0 && nx < R && ny >= 0 && ny < C);
    }
}
