import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());


        for (int test=1; test<=T; test++){
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            int max=0;
            int maxIdx=0;
            for (int i=0; i<N; i++){
                arr[i] = Integer.parseInt(st.nextToken());
                if(max<arr[i]){
                    max = arr[i];
                    maxIdx =i;
                }
            }
            long sum=0;
            for (int i=0; i<N; i++){
                if(arr[i]!=max){
                    sum+=max-arr[i];
                } else{
                    max=0;
                    maxIdx=0;
                    for (int j=i+1; j<N; j++){
                        if(max<arr[j]){
                            max = arr[j];
                            maxIdx = j;
                        }
                    }
                }
            }

            System.out.printf("#%d %d\n",test,sum);
        }
    }
}
