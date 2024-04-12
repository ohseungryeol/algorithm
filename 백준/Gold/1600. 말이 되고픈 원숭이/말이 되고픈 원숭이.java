
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[] hx = {-2, -2, -1, -1, 1, 1, 2, 2};
    static int[] hy = {-1, 1, -2, 2, -2, 2, -1, 1};
    static int[][] map;
    static int answer = Integer.MAX_VALUE;
    static boolean[][][] visited;
    static boolean flag;
    static int R,K,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine()); //k번 말 이동 가능
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        visited = new boolean[R][C][K + 1];

        for (int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();
        if(!flag) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void BFS(){
        Queue<int[]> queue = new LinkedList<>();
        // x,y,chance,count
        queue.offer(new int[]{0, 0, 0, 0});
        for (int i=0; i<=K; i++) visited[0][0][i] = true;


        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int chance = tmp[2];
            int count = tmp[3];
            if (tmp[0]==map.length-1 && tmp[1]==map[0].length-1){
                answer = Math.min(answer, count);
                flag = true;
            }
            // 찬스 한번도 안쓰는 경우
            /*
                *   0 0 0 0
                    1 0 0 0
                    0 0 1 0
                6   0 1 0 0
*
*
* *///        0 0 1 0 0 1 0 0 1 0
//            0 0 1 1 0 0 0 0 1 0
            for (int i=0; i<4; i++){
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];

                if(isRange(nx,ny) && map[nx][ny]==0 && !visited[nx][ny][chance]){
                    visited[nx][ny][chance]=true;
                    queue.offer(new int[]{nx, ny, chance, count + 1});
                }
            }
            if(chance < K) {
                for (int i = 0; i < 8; i++) {
                    int nx = tmp[0] + hx[i];
                    int ny = tmp[1] + hy[i];
                    if (isRange(nx, ny) && map[nx][ny] == 0 && !visited[nx][ny][chance + 1]) {
                        visited[nx][ny][chance + 1] = true;
                        queue.offer(new int[]{nx, ny, chance + 1, count + 1});
                    }
                }
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        return (nx>=0 && nx<R && ny>=0 && ny<C);
    }
}
