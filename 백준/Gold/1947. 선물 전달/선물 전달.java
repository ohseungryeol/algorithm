
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n =sc.nextInt();
        long[] dp = new long[1000001];
        long mod = 1000000000;
        dp[2]=1;
        dp[3]=2;

        for (int i=4; i<=n; i++){
            dp[i] = (i-1)*(dp[i-2]+dp[i-1])%mod;
        }

        System.out.println(dp[n]);


    }
}
