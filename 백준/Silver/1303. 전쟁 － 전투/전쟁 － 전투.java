import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int R,C;
    static int wSum=0;
    static int vSum=0;
    static int totalCount;
    static char[][] map;
    static boolean[][] check;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        C = sc.nextInt();
        R = sc.nextInt();
        map = new char[R][C];
        check = new boolean[R][C];

        for (int i=0; i<R; i++){
            String tmp = sc.next();
            for (int j=0; j<C; j++){
                map[i][j] = tmp.charAt(j);
            }
        }


        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if(!check[i][j] && map[i][j]=='W'){
                    check[i][j]=true;
                    totalCount=1;
                    int wCount=DFS(i,j,'W');
                    wSum+=wCount*wCount;
                } else if (!check[i][j] && map[i][j]=='B'){
                    totalCount=1;
                    check[i][j]=true;
                    int bCount = DFS(i,j,'B');
                    vSum+=bCount*bCount;
                }
            }
        }

        System.out.println(wSum+" "+vSum);
    }

    public static int DFS(int x, int y, char word){
        for (int i=0; i<4; i++){
            int nx= x+dx[i];
            int ny =y+dy[i];

            if(isRange(nx,ny) && !check[nx][ny] && map[nx][ny]==word){
                check[nx][ny]=true;
                totalCount++;
                DFS(nx,ny,word);
            }
        }
        return totalCount;
    }

    public static boolean isRange(int x, int y){
        if(x>=0 && x<R && y>=0 && y<C) return true;
        return false;
    }
}
