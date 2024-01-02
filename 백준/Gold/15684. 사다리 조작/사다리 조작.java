import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int R,C,N;
    static int[][] ladders;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        ladders = new int[R+1][C+1];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            ladders[s][e]=1;
        }

        for (int count=0; count<=3; count++){
            DFS(0,count);
        }
        System.out.println(-1);
    }

    public static void DFS(int L,int count){
        //3개를 놓는다.
        if(L==count){
            if(check()){
                System.out.println(count);
                System.exit(0);
            }
            return;
        }
        
        for (int i=1; i<=R; i++){
            for (int j=1; j<C; j++){
                if(ladders[i][j]==0 &&ladders[i][j+1]==0){ //가로선이 접하면 안됌
                    ladders[i][j]=1;
                    DFS(L+1,count);
                    ladders[i][j]=0;
                }
            }
        }
    }

    public static boolean check(){

        for (int i=1; i<=C; i++){ //시작 번호
            int start =i;
            for (int j=1; j<=R; j++){ //위에서 아래로
                if(ladders[j][start]==1){
                    start++;
                } else if (ladders[j][start-1]==1){
                    start--;
                }
            }
            if(start!=i) return false;
        }

        return true;
    }


}
