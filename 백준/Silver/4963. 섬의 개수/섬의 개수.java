
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, -1, 0, 1, -1, -1, 1, 1};
    static int[] dy = {-1, 0, 1, 0, -1, 1, 1, -1};
    static int[][] map;
    static boolean[][] visited;
    static int answer;
    static int R, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            if(C==0 && R==0) break;
            map = new int[R][C];
            visited = new boolean[R][C];
            answer=0;
            for (int i=0; i<R; i++){
                st = new StringTokenizer(br.readLine());
                for (int j=0; j<C; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for (int i=0; i<R; i++){
                for (int j=0; j<C; j++){
                    if(!visited[i][j] && map[i][j]==1){
                        DFS(i, j);
                        answer++;
                    }
                }
            }

            System.out.println(answer);
        }
    }

    public static void DFS(int x, int y){
        for (int i=0; i<8; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isRange(nx,ny) && !visited[nx][ny] && map[nx][ny]==1){
                visited[nx][ny]=true;
                DFS(nx, ny);
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        return (nx >= 0 && nx < R && ny >= 0 && ny < C);
    }
}
