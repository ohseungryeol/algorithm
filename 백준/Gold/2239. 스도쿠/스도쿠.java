import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static int[][] map = new int[9][9];
    static ArrayList<int[]> zeroList = new ArrayList<>();
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i=0; i<9; i++){
            String tmp = br.readLine();
            for (int j=0; j<9; j++){
                map[i][j] = Integer.parseInt(String.valueOf(tmp.charAt(j)));
                if(map[i][j]==0) zeroList.add(new int[]{i, j}); 
            }
        }
        DFS(0);

    }
    
    public static void DFS(int L){
        if(flag==true) return;
        if(L==zeroList.size()){
            for (int i=0; i<9; i++){
                for (int j=0; j<9; j++){
                    System.out.print(map[i][j]);
                }
                System.out.println();
            }

            flag =true;
            return;
        }
        int[] loc = zeroList.get(L);
        int curR = loc[0];
        int curC = loc[1];


        for (int num=1; num<=9; num++){
            if(rowCheck(curR,num) && colCheck(curC,num) && boxCheck(curR,curC,num)){
                map[curR][curC]=num;
                DFS(L+1);
                map[curR][curC]=0;
            }
        }
        
    }

    //가로
    public static boolean rowCheck(int r, int num){
        for(int i=0; i<9; i++){
            if(map[r][i]==num) return false;
        }

        return true;
    }
    //세로
    public static boolean colCheck(int c, int num){
        for (int i=0; i<9; i++){
            if(map[i][c]==num) return false;
        }

        return true;
    }
    

    
    //박스
    public static boolean boxCheck(int r, int c, int num){
        int startR = (r/3)*3;
        int startC = (c/3)*3;

        for (int i=startR; i<startR+3; i++){
            for (int j=startC; j<startC+3; j++){
                if(map[i][j]==num) return false;
            }
        }

        return true;
    }
    
    
    
    
}
