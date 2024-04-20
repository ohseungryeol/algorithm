
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 배열 길이
        int K = Integer.parseInt(st.nextToken());
        String tmp = br.readLine();
        /*
        * 사람은 거리가 k 이하인 햄버거만
         햄버거를 먹을 수 있는 사람의 최대 수
        * */

        char[] arr = new char[N];
        boolean[] check = new boolean[N];
        for (int i=0; i<N; i++){
            arr[i] = tmp.charAt(i);
        }

        //멀리 떨어진 햄버거부터 먹
        // H H H H H P P P P P H P H P H P H H H P
        int answer=0;
        for (int i=0; i<arr.length; i++){
            if(arr[i]=='H') continue;
            for (int j=i-K; j<=i+K; j++){
                if(j<0 || j>=N) continue;
                if(arr[j]=='H'&& !check[j] ){
                    check[j]=true;
                    answer++;
                    break;
                }
            }
        }

        System.out.println(answer);
    }
}
