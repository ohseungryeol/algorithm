import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //굴다리 길이
        int M = Integer.parseInt(br.readLine()); // 가로등 갯수

        int[] loc = new int[M]; //가로등 위치 담은 배열

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i=0; i<M; i++){
            loc[i] = Integer.parseInt(st.nextToken());
        }

        // 일단 첫번째 가로등과 마지막 가로등은 은 0과 N을 무조건 비춰야함.
        int min = Integer.MAX_VALUE;
        for (int i=0; i<=100000; i++){
            if(loc[0]-i<=0 && loc[M-1]+i >=N){
                min =i;
                break;
            }
        }

       // System.out.println(min);
        //System.out.println(min);
        // 3, 6, 9, 12
        //System.out.println(min);
        int cur=1; //현재
        int prev=0;
        int prevRight = loc[prev]+min;

        while(true){
            if(cur==M) break;
            int curLeft = loc[cur]-min;

            if(prevRight<curLeft){
                min++;
                prevRight = loc[prev]+min;
            } else{ // 같아지면 다음 가로등으로
                prevRight = loc[cur]+min;
                cur++;
                prev++;
            }
        }


        System.out.println(min);

    }
}
