import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class Solution {
	static int answer;
    static int[][] dp;
    static int num;
    static int maxCalory;
    static int[] value;
    static int[] calory;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int test=1; test<=T; test++){
            st = new StringTokenizer(br.readLine());
            num = Integer.parseInt(st.nextToken());
            boolean[] check = new boolean[num];
            maxCalory = Integer.parseInt(st.nextToken());
            answer=0;
            value = new int[num];
            calory = new int[num];
            dp = new int[21][10001];
            for (int i=0; i<num; i++){
                st = new StringTokenizer(br.readLine());
                value[i] = Integer.parseInt(st.nextToken());
                calory[i] = Integer.parseInt(st.nextToken());
            }
            System.out.printf("#%d %d\n",test,get_maxValue(0,0));
        }
    }

    public static int get_maxValue(int L, int W){
        int n1=0; //포함
        int n2=0; //미포함
        if(dp[L][W]>0) return dp[L][W];
        if(L==num) return 0;

        if(W+calory[L]<=maxCalory){
            n1 = value[L]+get_maxValue(L+1,W+calory[L]);
        }
        n2 = get_maxValue(L + 1, W);

        return dp[L][W] = Math.max(n1, n2);

    }
}