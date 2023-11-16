
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int outSand =0;
    static int N;

    static int[][] windX = {{-1, 1, -2, 2, 0, -1, 1, -1, 1}, //좌
                            {-1, -1, 0, 0, 2, 0, 0, 1, 1}, //하
                            {1, -1, 2, -2, 0, 1, -1, 1, -1}, //우
                            {1, 1, 0, 0, -2, 0, 0, -1, -1} //상
    };
    static int[][] windY = {{1, 1, 0, 0, -2,0, 0, -1, -1}, //좌
                            {-1, 1, -2, 2, 0, -1, 1, -1, 1}, //하
                            {-1, -1, 0, 0, 2, 0, 0, 1, 1}, //우
                            {1, -1, 2, -2, 0, 1, -1, 1, -1} //상
    };

    static int[] rate = {1, 1, 2, 2, 5, 7, 7, 10, 10};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int sX = N/2;
        int sY = N/2;
        solve(sX,sY);
    }

    public static void solve(int sX, int sY){
        visited[sX][sY]=true;
        int d=-1;
        while(true){
            if(sX==0 && sY==0){
                System.out.println(outSand);
                break;
            }
            int nd = (d+1)%4; //다음으로 이동해야 할 방향
            //좌 하 우 상 순으로 토네이도이동

            int nx = sX+dx[nd];
            int ny = sY+dy[nd];

            if(visited[nx][ny]){
                //다음으로 갈 방향이 이미 방문한 곳이라면 방향을 전에갔던 방향으로 유지해야함
                nx = sX+dx[d]; //이전 갔던 방향 유지
                ny = sY+dy[d];
                spread(nx,ny,d);
                sX= nx;
                sY= ny;
            } else {
                spread(nx,ny,nd);
                sX =nx;
                sY =ny;
                d=nd;
            }


        }

    }

    public static void spread(int x, int y, int d){
        visited[x][y]=true;

        //현재 d는 (좌우상하) 방향을 나타냄
        int sand = map[x][y];
        int totSpread =0;
        for (int i=0; i<9; i++){ //인덱스
            int dx = x+windX[d][i];
            int dy = y+windY[d][i];

            int spreadSand = (sand*rate[i])/100;
            totSpread+=spreadSand;
            if(!isRange(dx,dy)){ //범위 벗어나면 out 추가
                outSand+=spreadSand;
            } else{
                
                map[dx][dy]+=spreadSand; //범위 내면 퍼뜨림
            }
        }

        int aX = x+dx[d];
        int aY = y+dy[d];
        if(!isRange(aX,aY)) outSand+=sand-totSpread;
        else map[aX][aY]+= sand-totSpread;


    }

    public static boolean isRange(int nx, int ny){
        if(nx>=0 && nx<N &&ny>=0 &&ny<N ) return true;
        return false;
    }


}
