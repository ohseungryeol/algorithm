import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static boolean[] check;
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        check = new boolean[N + 1];

        for (int i=1; i<=N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=1; j<=N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backT(0,1);
        System.out.println(answer);
    }

    public static void backT(int L,int start){

        if(L==N/2){
            diffTeams();
            return;
        }
        for (int i=start; i<=N; i++){
            if(!check[i]){
                check[i]=true;
                backT(L+1,i+1);
                check[i]=false;
            }
        }
    }

    public static void diffTeams(){
        int start =0;
        int link =0;

        for (int i=1; i<=N-1; i++){
            for (int j=i+1; j<=N; j++){
                if(check[i] && check[j]){
                    start+=map[i][j];
                    start+=map[j][i];
                } else if (!check[i] && !check[j]){
                    link+=map[i][j];
                    link+=map[j][i];
                }
            }
        }

        answer = Math.min(answer, Math.abs(start - link));
    }
}
