import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    static int[] move;
    static int[][][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        move = new int[line.length-1];
        for(int i=0; i<line.length-1; i++) {
            move[i] = Integer.parseInt(line[i]);
        }

        dp = new int[5][5][line.length]; //왼발 , 오른발 , 도착
        for(int i=0; i<5; i++) {
            for(int j=0; j<5; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }
        System.out.println(process(0,0,0));
        }


    public static int process (int left, int right, int L){
        if(L== move.length) return 0;
        int n1= 0; //왼쪽
        int n2 = 0; //오른쪽
        if(dp[left][right][L]>-1) return dp[left][right][L];
        
        n1 = process(move[L],right,L+1)+energy(left,move[L]);
        n2 = process(left,move[L],L+1)+energy(right,move[L]);

        return dp[left][right][L] = Math.min(n1, n2);
    }

    public static int energy(int start, int end) {
        if(start ==0) return 2;
        else if (start==end) return 1;
        else if (Math.abs(start-end)==2) return 4;
        else return 3;
    }


}
