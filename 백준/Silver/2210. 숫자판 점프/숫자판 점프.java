
import java.util.HashSet;
import java.util.Scanner;

public class Main {
    static int[][] arr = new int[5][5];
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited= new boolean[5][5];
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                arr[i][j]=sc.nextInt();
            }
        }
        for (int i=0; i<5; i++){
            for (int j=0; j<5; j++){
                DFS(0, i, j, "");
            }
        }
        System.out.println(set.size());
    }

    public static void DFS(int L, int x ,int y,String num){

        if(set.contains(num)) return;
        if(L==6){
            set.add(num);
            return;
        }

        for (int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];

            if(isRange(nx,ny)){
                DFS(L+1,nx,ny,num+String.valueOf(arr[nx][ny]));

            }
        }
    }

    public static boolean isRange(int x, int y){
        if(x>=0 && x<5 && y>=0 && y<5) return true;
        return false;
    }
}
