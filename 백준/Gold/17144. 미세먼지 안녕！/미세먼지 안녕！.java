
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static int R,C,T;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, -0, 1};
    static int[][] tempMap;
    static Queue<int[]> queue = new LinkedList<>();
    static ArrayList<int[]> AirPurifier = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        tempMap = new int[R][C];

        for (int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j]==-1) AirPurifier.add(new int[]{i, j});
            }
        }

        while(T-->0){
            map=BFS();
        }


        System.out.println(getSum());
    }

    public static int[][] BFS(){
        tempMap = new int[R][C];
            for (int i=0; i<R; i++){
                for (int j=0; j<C; j++){

                    if(map[i][j]!=-1 && map[i][j]!=0){
                        queue.offer(new int[]{i, j});
                        tempMap[i][j] += map[i][j];
                    } else if(map[i][j]==-1){
                        tempMap[i][j]=-1;
                    }
                }
            }

            while (!queue.isEmpty()) {
                int[] tmp = queue.poll();
                int curX = tmp[0];
                int curY = tmp[1];
                for (int i = 0; i < 4; i++) {
                    int nx = curX + dx[i];
                    int ny = curY + dy[i];

                    if (isRange(nx, ny)) {
                        tempMap[nx][ny] += map[curX][curY] / 5;
                        tempMap[curX][curY] -= map[curX][curY]/5;

                    }

                }

            }

        moveAir();
        return tempMap;

    }

    public static void moveAir(){

        //위쪽 공기청정기 (반시계방향)
        int[] airPur = AirPurifier.get(0);
        int curX = airPur[0];
        int curY = airPur[1];

        //아래쪽
        for(int i=curX-1; i>0; i--){
            tempMap[i][curY] = tempMap[i - 1][curY];
        }

        //왼쪽
        for(int i=0; i<C-1; i++){
            tempMap[0][i]=tempMap[0][i+1];
        }

        //위쪽
        for (int i=0; i<curX; i++){
            tempMap[i][C-1]=tempMap[i+1][C-1];
        }

        //오른쪽
        for (int i=C-1; i>curY; i--){
            tempMap[curX][i] = tempMap[curX][i-1];
        }
        tempMap[curX][1]=0;

        /*아래쪽 공기청정기*/
        airPur = AirPurifier.get(1);
        curX = airPur[0];
        curY = airPur[1];

        //위쪽
        for(int i=curX+1; i<R-1; i++){
            tempMap[i][curY] = tempMap[i+1][curY];
        }

        //왼쪽
        for(int i=0; i<C-1; i++){
            tempMap[R - 1][i] = tempMap[R - 1][i + 1];
        }

        //아래쪽
        for(int i=R-1; i>curX; i--){
            tempMap[i][C - 1] = tempMap[i - 1][C - 1];
        }


        //오른쪽
        for (int i=C-1; i>curY; i--){
            tempMap[curX][i] = tempMap[curX][i-1];
        }
        tempMap[curX][1]=0;

    }

    public static int getSum(){
        int sum=0;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if(map[i][j]!=-1){
                    sum+=map[i][j];
                }
            }
        }

        return sum;
    }

    public static boolean isRange(int nx, int ny){
        if(nx>=0 && nx<R && ny>=0 && ny<C && map[nx][ny]!=-1) return true;
        return false;
    }
}
