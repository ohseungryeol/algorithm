
import java.util.Scanner;

public class Main {
    static char[][] map;
    static boolean[][] visited;
    static int R,C;
    static int answer=0;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    static int[] alpha = new int[26];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        map = new char[R][C];
        visited = new boolean[R][C];
        for (int i=0; i<R; i++){
            String tmp = sc.next();
            for (int j=0; j<C; j++){
                map[i][j]=tmp.charAt(j);
            }
        }
        DFS(0,0,1);
        System.out.println(answer);
    }

    public static void DFS(int x, int y, int count){
        alpha[map[x][y]-'A']++;
        answer = Math.max(answer,count);
        for (int k=0; k<4; k++){
            int nx = x+dx[k];
            int ny = y+dy[k];
            if(isRange(nx,ny) && alpha[map[nx][ny]-'A']==0){
                DFS(nx,ny,count+1);
                alpha[map[nx][ny]-'A']--;

            }
        }

    }

    public static boolean isRange(int nx, int ny){
        if(nx>=0 && nx<R && ny>=0 && ny<C) return true;
        return false;
    }
}
