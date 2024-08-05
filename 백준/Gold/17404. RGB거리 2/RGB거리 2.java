
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N][3];
        int[][] cost = new int[N][3];
        
        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                cost[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // N<=1000

        for (int[] arr:dp){
            Arrays.fill(arr, 1001);
        }

        // 첫번째 집을 R로칠했을 때, G로 칠했을 떄, B로 칠했을 때의 비용을 각각 구한다.
        int answer = Integer.MAX_VALUE;
        for (int i=0; i<3; i++){

            // 첫번째를 고정 시킨다.
            for (int k=0; k<3; k++){
                if(i==k){
                    dp[0][k]=cost[0][k];
                } else{
                    dp[0][k]=1001;
                }
            }

            for (int j=1; j<N; j++){
                dp[j][0] = Math.min(dp[j - 1][1], dp[j - 1][2]) + cost[j][0];
                dp[j][1] = Math.min(dp[j - 1][0], dp[j - 1][2]) + cost[j][1];
                dp[j][2] = Math.min(dp[j - 1][0], dp[j - 1][1]) + cost[j][2];
            }

            for (int n=0; n<3; n++){
                if(i==n) continue; // 첫번째와 같은 색 불가
                answer = Math.min(answer,dp[N-1][n]);
            }
        }

        System.out.println(answer );


    }


}
