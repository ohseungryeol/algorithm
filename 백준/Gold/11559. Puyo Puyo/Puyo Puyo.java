import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int R,C;
    static int cnt;
    static int answer;
    static boolean[][] visited;
    static boolean[] colCheck;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        R =12;
        C =6;
        answer=0;

        map = new char[R][C];

        for (int i=0; i<R; i++){
            String tmp = br.readLine();
            for (int j=0; j<C; j++){
                map[i][j] = tmp.charAt(j);
            }
        }
        //Todo
        // 1.DFS 작업으로 같은 칸 터뜨리기
        // 2. 터뜨린 칸 위에서부터 내리기
        // 3. 더이상 터질게 없을 때 까지 반복.
        int count=0;


        while(true) {
            visited = new boolean[R][C];
            colCheck = new boolean[C];
            boolean isBomb = false;
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    cnt = 0;

                    if (map[i][j] != '.' && !visited[i][j]) {
                        boolean[][] tmpVisited = DFS(i, j, map[i][j], new boolean[R][C]);
                        //한번이라도 터졌다면 연쇄다.
                        if (cnt >= 4) {
                            isBomb =true;
                            // 4개 이상일 때만 메인 visited에 저장
                            for (int r=0; r<R; r++){
                                for (int c=0; c<C; c++){
                                    if(tmpVisited[r][c]){
                                        visited[r][c]=true;
                                        colCheck[c]=true;
                                    }
                                }
                            }
                        }
                        // 내리기 로직
                    }
                }
            }
            // 뿌요가 터졌다면 내려야지.
//            count++;
//            for (int i=0; i<R; i++){
//                for (int j=0; j<C; j++){
//                    System.out.print(visited[i][j]+" ");
//                }
//                System.out.println();
//            }
//            System.out.println();
//            if(count>5) break;
            if(isBomb){
                answer++;
                // 터진 것들은 먼저 .으로 바꿔준다.
                for (int i=0; i<R; i++){
                    for (int j=0; j<C; j++){
                        if(visited[i][j]) map[i][j]='.';
                    }
                }
                downPuyo();
            } else{
                break;
            }
//            System.out.println("===================================================== 터진 후 배열==========");
//            for (int i=0; i<R; i++){
//                for (int j=0; j<C; j++){
//                    System.out.print(map[i][j]+" ");
//                }
//                System.out.println();
//            }
        }

        System.out.println(answer);
    }

    public static void downPuyo(){
        //열마다 내릴 대상의 마지막 행을 찾는다.


        for (int c=0; c<C; c++){
            if(!colCheck[c]) continue;
            int cnt=0;
            int up=13;
            boolean flag = false;
            for (int r=0; r<R; r++){

                if(visited[r][c]){
                    flag = true; //뿌요시작
                    cnt++;
                    up = Math.min(up,r-1);
                    if(r==R-1){
                        for (int i=up; i>=0; i--){
                            if(map[i][c]!='.'){
                                map[i+cnt][c]=map[i][c];
                                map[i][c]='.';
                            }
                        }
                    }
                } else if(flag && !visited[r][c]){
                    //현재 r 전까지 내린다.
                    // cnt=4;
                    for (int i=up; i>=0; i--){
                        if(map[i][c]!='.'){
                            map[i+cnt][c]=map[i][c];
                            map[i][c]='.';
                        }
                    }
                    flag=false;
                    cnt=0;
                    up=13;

                }
            }
        }

    }

    public static boolean[][] DFS(int x, int y,char c, boolean[][] tmpVisited){
        cnt++;
        tmpVisited[x][y]=true;
        for (int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(isRange(nx,ny) && !tmpVisited[nx][ny] && map[nx][ny]==c){
                DFS(nx, ny, c,tmpVisited);
            }
        }

        return tmpVisited;
    }

    public static boolean isRange(int nx ,int ny){
        return (nx>=0 && nx<R && ny>=0 && ny<C);
    }
}
