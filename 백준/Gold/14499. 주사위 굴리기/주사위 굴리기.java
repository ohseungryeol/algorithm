import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] dice = {0, 0, 0, 0, 0, 0}; // 앞 뒤 밑 왼 오 위
    // 동 서 북 남 순
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    static int R,C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken()); //이동횟수

        int[][] map = new int[R][C];

        for (int i=0; i<R; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<C; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        주사위를 굴려 이동한 칸이 0이면 -> 주사위 바닥면에 수가 칸으로 복사
//        이동한 칸이 0이 아니면 -> 칸에 수가 주사위 바닥면으로 복사, 칸은 0으로 바뀜
//
//        A: 이동할 때마다 주사위 윗면에 쓰여있는 수 출력.
        st = new StringTokenizer(br.readLine());
        //앞 뒤 밑 왼 오 위
        for (int i=0; i<k; i++){
            int dir = Integer.parseInt(st.nextToken());
            // 굴릴 수 있는가.
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            if(!isRange(nx,ny)) continue;

            //주사위 굴리기
            diceProcess(dir);
            if(map[nx][ny]==0){ //이동한 칸이 0이면
                map[nx][ny]=dice[2];
            } else {
                dice[2]=map[nx][ny];
                map[nx][ny]=0;
            }
            x=nx;
            y=ny;
            System.out.println(dice[5]);
        }

    }

    public static void diceProcess(int dir){
        //앞 뒤 밑 왼 오 위
        if(dir==1){ //동
            int tmp1 = dice[2];
            int tmp2 = dice[3];

            dice[2]=dice[4]; //밑 ->오
            dice[3]=tmp1;//왼->밑
            dice[4]=dice[5]; //오 ->위
            dice[5]=tmp2;//위 ->왼


        } else if (dir==2){ //서
            int tmp1 = dice[2];
            int tmp2 = dice[4];
            dice[2]=dice[3]; //밑 -> 왼
            dice[3]=dice[5]; //왼 -> 위
            dice[4]=tmp1; //오 -> 밑
            dice[5]=tmp2; // 위 ->오
        } else if (dir==3){ //북
            //앞 뒤 밑 왼 오 위
            int tmp1 = dice[0];
            int tmp2 = dice[1];
            dice[0]=dice[2]; //앞 ->밑
            dice[1]=dice[5]; //뒤 ->위
            dice[2]=tmp2;//밑 ->뒤
            dice[5]=tmp1;//위->앞
        } else { //남
            int tmp1 = dice[1];
            int tmp2 = dice[0];
            dice[0]=dice[5]; //앞 ->위
            dice[1]=dice[2]; //뒤 ->밑
            dice[2]=tmp2;//밑 ->앞
            dice[5]=tmp1;//위->뒤
        }
    }
    public static boolean isRange(int nx, int ny){
        return (nx >= 0 && nx < R && ny >= 0 && ny < C);
    }
}
