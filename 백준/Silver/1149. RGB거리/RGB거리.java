import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp ;
    static int[][] cost ;
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N][3];
        cost = new int[N][3];

        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int[] arr:dp){
            Arrays.fill(arr, Integer.MAX_VALUE);
        }

        //초기화.
        dp[0][0]=cost[0][0];
        dp[0][1]=cost[0][1];
        dp[0][2]=cost[0][2];

        for (int i=1; i<N; i++){
            dp[i][0] = Math.min(dp[i-1][1],dp[i-1][2])+cost[i][0];
            dp[i][1] = Math.min(dp[i-1][0],dp[i-1][2])+cost[i][1];
            dp[i][2] = Math.min(dp[i-1][0],dp[i-1][1])+cost[i][2];
        }

        int answer = Math.min(dp[N-1][0],Math.min(dp[N-1][1],dp[N-1][2]));
        System.out.println(answer);
    }

}
