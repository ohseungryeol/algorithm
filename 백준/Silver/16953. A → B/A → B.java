import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static long A,B;
    static long answer=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());

        DFS(String.valueOf(A),1);
        if(answer==0) System.out.println(-1);
        else System.out.println(answer);
    }

    public static void DFS(String num,long count){
        long n = Long.parseLong(num);
        if(n>B) return;
        if(n==B){
            answer=count;
            return;
        }

        DFS(String.valueOf(n*2),count+1);
        DFS(num+"1",count+1);
    }

}
