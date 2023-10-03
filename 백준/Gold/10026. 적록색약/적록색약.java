
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;
    static int N;
    static int answer = 0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new boolean[N][N];
        /*입력부*/
        for (int i = 0; i < N; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = tmp.charAt(j);
            }
        }

        /*적록색약 x*/
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    answer++;
                    DFS(i, j, map[i][j]);
                }
            }
        }

        System.out.print(answer + " ");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 'G') map[i][j] = 'R';
            }
        }

        visited = new boolean[N][N];
        answer = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    visited[i][j] = true;
                    answer++;
                    DFS(i, j, map[i][j]);
                }
            }
        }

        System.out.println(answer);
    }

    public static void DFS(int x, int y, char color){
        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(isRange(nx,ny) && map[nx][ny]==color && !visited[nx][ny]){
                visited[nx][ny]=true;
                DFS(nx,ny,color);
            }
        }
    }

    public static boolean isRange ( int nx, int ny){
        if (nx >= 0 && nx < N && ny >= 0 && ny < N) {
            return true;
        }

        return false;
    }
}
