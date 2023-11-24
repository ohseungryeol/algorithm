
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
    static int N,K;
    static int[] arr;
    static boolean[] check;
    static int answer=0;
    static HashSet<String> set = new HashSet<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();


        // K장을 선택해서 만들 수 있는 정수
        arr = new int[N];
        check = new boolean[N];
        for (int i=0; i<N; i++){
            arr[i]=sc.nextInt();
        }

        DFS(0, "");
        
        System.out.println(set.size());
    }

    public static void DFS(int cnt,String num){
       
        if(cnt==K){
            set.add(num);
            return;
        }

        for (int i=0; i<N; i++){
            if(!check[i]) {
                check[i]=true;
                DFS(cnt + 1, num + String.valueOf(arr[i]));
                check[i]=false;
            }
        }
    }
}
