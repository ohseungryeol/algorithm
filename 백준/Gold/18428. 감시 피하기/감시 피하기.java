import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static char[][] map;
    static boolean[][] check;
    static List<int[]> teachers = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        check = new boolean[N][N];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = st.nextToken().charAt(0);
                if(map[i][j]=='T') teachers.add(new int[]{i, j});
            }
        }

        DFS(0);
        System.out.println("NO");
    }

    public static void DFS(int L){
        if(L==3){
            if(!leftSearch() && !rightSearch() &&!downSearch() && !upSearch()){
                System.out.println("YES");
                System.exit(0);
            }

            return;
        }

        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                if(map[i][j]=='X' && !check[i][j]){
                    map[i][j]='O';
                    check[i][j]=true;
                    DFS(L+1);
                    check[i][j]=false;
                    map[i][j]='X';
                }
            }
        }
    }

    public static boolean leftSearch(){

        for(int[] teacher:teachers){
            boolean wall=false;
            int curX = teacher[0];
            int curY = teacher[1];

            for (int i=curY-1; i>=0; i--){
                if(map[curX][i]=='O'){
                    wall=true;
                }
                if(map[curX][i]=='S' && !wall){
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean rightSearch(){

        for(int[] teacher:teachers){
            boolean wall=false;
            int curX = teacher[0];
            int curY = teacher[1];

            for (int i=curY+1; i<N; i++){
                if(map[curX][i]=='O'){
                    wall=true;
                }
                if(map[curX][i]=='S' && !wall){
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean upSearch(){

        for(int[] teacher:teachers){
            boolean wall=false;
            int curX = teacher[0];
            int curY = teacher[1];

            for (int i=curX-1; i>=0; i--){
                if(map[i][curY]=='O'){
                    wall=true;
                }
                if(map[i][curY]=='S' && !wall){
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean downSearch(){

        for(int[] teacher:teachers){
            boolean wall=false;
            int curX = teacher[0];
            int curY = teacher[1];

            for (int i=curX+1; i<N; i++){
                if(map[i][curY]=='O'){
                    wall=true;
                }
                if(map[i][curY]=='S' && !wall){
                    return true;
                }
            }

        }
        return false;
    }

    public static boolean isRange(int x, int y){
        if(x>=0 && x<N && y>=0 && y<N) return true;

        return false;
    }

}
