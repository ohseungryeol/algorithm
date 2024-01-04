
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][3];
        int[][] dp = new int[N][3]; // i번째 집까지 0(r) 1(g) 2(b)로 칠했을 때의 최소비용


        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }





        //인접한 칸과 같은 색 X


        //1번 집의 색은 2번, N번 집의 색과 같지 않아야 한다.
        //N번 집의 색은 N-1번, 1번 집의 색과 같지 않아야 한다.
        //i(2 ≤ i ≤ N-1)번 집의 색은 i-1, i+1번 집의 색과 같지 않아야 한다.

        //26 40 83  -> 83 선택
        //49 60 57  -> 자동으로 2번쨰거 선택
        //13 89 99  -> 89 선택
        int answer = Integer.MAX_VALUE;
        for (int i=0; i<N; i++){
            for (int k=0; k<3; k++){
                if(k==i) dp[0][k] =arr[0][i];
                else dp[0][k]=1000001;
            }
            for (int j=1; j<N; j++){

                dp[j][0] = Math.min(dp[j - 1][1] + arr[j][0], dp[j - 1][2] + arr[j][0]);
                dp[j][1] = Math.min(dp[j - 1][0] + arr[j][1], dp[j - 1][2] + arr[j][1]);
                dp[j][2] = Math.min(dp[j - 1][0] + arr[j][2], dp[j - 1][1] + arr[j][2]);
            }

            for (int n=0; n<3; n++){
                if(n==i) continue;
                answer = Math.min(answer,dp[N-1][n]);
            }

        }

        //모든 경우의 수에서 가장 작은걸로
        System.out.println(answer);


    }


}
