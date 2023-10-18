import java.io.*;
import java.util.*;
public class Main {
    static int n,m,k,r,c;
    static int map[][];
    static int ans =0;
    static int stiker[][];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String[] t = br.readLine().split(" ");
        n = Integer.parseInt(t[0]);
        m = Integer.parseInt(t[1]);
        k = Integer.parseInt(t[2]);
        map = new int[n][m];
        
        while(k-- >0) {
            String[] tt = br.readLine().split(" ");
            r = Integer.parseInt(tt[0]);
            c = Integer.parseInt(tt[1]);
            stiker = new int[r][c];
            for(int i=0; i<r; i++) {
                String[] stiker_input = br.readLine().split(" ");
                for(int j=0; j<c; j++) {
                    stiker[i][j] = Integer.parseInt(stiker_input[j]);
                }
            }
            
            for(int i=0; i<4; i++) {
                if(put_stiker()) {
                    break;
                }
                else {
                    rotate();
                }
            }
        }
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(map[i][j]==1) {
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
    public static boolean put_stiker() {
        for(int i=0; i<=n-r; i++) {
            for(int j=0; j<=m-c; j++) {
                if(isPossible(i,j)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isPossible(int row, int col) {
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(stiker[i][j]==1 && map[row+i][col+j]==1) {
                    return false;
                }
            }
        }
        
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                if(stiker[i][j]==1) {
                    map[row+i][col+j]=1;
                }
            }
        }
        return true;
    }
    public static void rotate() {
        int rotate_map[][] = new int[r][c];
        for(int i=0; i<r; i++) {
            for(int j=0; j<c; j++) {
                rotate_map[i][j] = stiker[i][j];
            }
        }
        
        stiker = new int[c][r];
        
        for(int i=0; i<c; i++) {
            for(int j=0; j<r; j++) {
                stiker[i][j] = rotate_map[r-j-1][i];
            }
        }
        int tmp = r;
        r=c;
        c=tmp;
    }
}