import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] visited;
    static boolean[][] fireVisted;
    static int fireLen;
    static int jeehunLen;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static Queue<int[]> fire = new LinkedList<>();
    static Queue<int[]> jeehun = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new boolean[R][C];
        fireVisted = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = tmp.charAt(j);
                if (map[i][j] == 'J'){
                    jeehun.offer(new int[]{i, j, 0});
                    visited[i][j]=true;
                }
                else if (map[i][j] == 'F') {
                    fire.offer(new int[]{i, j, 0});
                    fireVisted[i][j] = true;
                }
            }
        }



        int answer = BFS();
        if (answer == 0) {
            System.out.println("IMPOSSIBLE");
        } else {
            System.out.println(answer);
        }
    }

    public static int BFS() {
//  #F..#
//  #.J.#
//  ###.#
//  ###.#
//  ###.#

        while (!jeehun.isEmpty()) {
            fireLen = fire.size();
            //깊이가 0부터 확산
            for (int f=0; f< fireLen; f++) {
                int[] fTmp = fire.poll();
                int fx = fTmp[0];
                int fy = fTmp[1];

                // 불 먼저 4방향 확산
                for (int i = 0; i < 4; i++) {
                    int nfx = fx + dx[i];
                    int nfy = fy + dy[i];

                    if (isRange(nfx, nfy) && !fireVisted[nfx][nfy] && map[nfx][nfy] != '#') {
                        fireVisted[nfx][nfy] = true;
                        fire.offer(new int[]{nfx, nfy, 0});
                    }
                }
            }
            jeehunLen = jeehun.size();
            for (int x=0; x<jeehunLen; x++){
                if (jeehun.isEmpty()) break;
                int[] jTmp = jeehun.poll();
                int jx = jTmp[0];
                int jy = jTmp[1];
                int cnt = jTmp[2];


                if (jx == 0 || jy == 0 || jx == R - 1 || jy == C - 1) return cnt + 1;
                if (isConfined(jx, jy)) return 0;

                for (int i = 0; i < 4; i++) {
                    int njx = jx + dx[i];
                    int njy = jy + dy[i];

                    if (isRange(njx, njy) && map[njx][njy] == '.' && !visited[njx][njy] && !fireVisted[njx][njy]) {
                        visited[njx][njy] = true;
                        jeehun.offer(new int[]{njx, njy, cnt + 1});
                    }
                }
            }

        }


        return 0;
    }


    public static boolean isRange(int nx, int ny) {
        return (nx >= 0 && nx < R && ny >= 0 && ny < C);
    }

    public static boolean isConfined(int x, int y) {

        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            //갈 수있는 모든 방향이 #이면 갇힌 것
            if (isRange(nx, ny) && map[nx][ny] != '#') return false;
        }

        return true;
    }
}
