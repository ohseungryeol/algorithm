
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        int[][] dp = new int[N][3]; // i번째 집까지 0(r) 1(g) 2(b)로 칠했을 때의 최소비용


        for (int i=0; i<N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0; j<3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //초기화
        for (int i=0; i<N; i++){
            for (int j=0; j<3; j++){
                dp[i][j]=10000001;
            }
        }



        //인접한 칸과 같은 색 X

        dp[0][0] = arr[0][0];
        dp[0][1] = arr[0][1];
        dp[0][2] = arr[0][2];

        for (int j=1; j<N; j++){
            //j번째 집을 0(r)으로 칠했을 때 최소비용
            dp[j][0] = Math.min(dp[j - 1][1] + arr[j][0], dp[j - 1][2] + arr[j][0]);
            dp[j][1] = Math.min(dp[j - 1][0] + arr[j][1], dp[j - 1][2] + arr[j][1]);
            dp[j][2] = Math.min(dp[j - 1][0] + arr[j][2], dp[j - 1][1] + arr[j][2]);
        }

        System.out.println(Math.min(dp[N-1][2],Math.min(dp[N-1][0],dp[N-1][1])));


    }
}
