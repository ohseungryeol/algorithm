
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int cnt;
    static boolean[] visited;
    static char[][] map;
    static int answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[5][5];
        boolean[][] check = new boolean[5][5];
        visited = new boolean[25];
        for (int i=0; i<5; i++){
            String tmp = br.readLine();
            for (int j=0; j<5; j++){
                map[i][j] = tmp.charAt(j);
            }
        }

        Combi(0,0,check);


        System.out.println(answer);
    }


    public static void Combi(int L,int start, boolean[][] check){

        if(L==7){
            // 이다솜파가 더 많다면
            if(Calculate(check)){
                int x=0,y=0;
                boolean flag=false;
                boolean[][] dfCheck = new boolean[5][5];
                for (int i=0; i<5; i++){
                    for (int j=0; j<5; j++){
                        if(check[i][j]){
                            x=i;
                            y=j;
                            flag = true;
                            dfCheck[i][j]=true;
                            break;
                        }
                    }
                    if(flag) break;
                }
                cnt=0;
                DFS(x,y,check,dfCheck);
                if(cnt==7) answer++;
            }
            return;
        }

        // Y Y Y Y Y S Y S Y S Y Y Y Y Y Y S Y Y S Y Y Y Y Y

        for (int i=start; i<25; i++){
            if(!visited[i]){
                visited[i]=true;
                check[i/5][i%5]=true;
                Combi(L+1,i+1,check);
                visited[i]=false;
                check[i/5][i%5]=false;
            }
        }

    }
    // 7공주가 다 연결되어있는지 확인.
    public static void DFS(int x, int y, boolean[][] check,boolean[][] dfCheck){

        cnt++;
        for (int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            // 7공주가 이어져있는가
            if(isRange(nx,ny) && !dfCheck[nx][ny] && check[nx][ny]){
                dfCheck[nx][ny]=true;
                DFS(nx,ny,check,dfCheck);
            }
        }
    }

    public static boolean Calculate(boolean[][] check){
        int s=0;
        int y=0;
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                if(check[i][j]){
                    if(map[i][j]=='S'){
                        s++;
                    } else {
                        y++;
                    }
                }
            }
        }

        if(s>y){
            return true;
        }
        return false;
    }

    public static boolean isRange(int nx, int ny){
        return (nx >= 0 && nx < 5 && ny >= 0 && ny < 5);
    }
}
