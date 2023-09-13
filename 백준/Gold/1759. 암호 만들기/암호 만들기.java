
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    static int R,C;
    static boolean[] check;
    static char[] arr;
    static char[] crypto;
    public static void main(String[] args) throws IOException {
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        arr = new char[C];
        for (int i=0; i<C; i++){
            arr[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(arr);
        /*C개의 문자열중 R개의 암호-> cCr*/
        crypto = new char[R];
        check = new boolean[C];
        DFS(0,0);
    }

    public static void DFS(int L, int idx){
        if(L==R){
            if(isValid()) {
                for (int i=0; i<crypto.length; i++) System.out.print(crypto[i]);
                System.out.println();
            }
            return;
        }

        for (int i=idx; i<arr.length; i++){
            if(!check[i]){
                check[i]=true;
                crypto[L]=arr[i];
                DFS(L+1,i);
                check[i]=false;
            }
        }
       // if(check[])
    }

    // 최소 모음 1개, 최소 자음 2개인지 확인
    public static boolean isValid() {
        int mo = 0;
        int ja = 0;

        for (char x : crypto) {
            if (x == 'a' || x == 'e' || x == 'i' || x == 'o' || x == 'u') {
                mo++;
            } else {
                ja++;
            }
        }

        if (mo >= 1 && ja >= 2) {
            return true;
        }
        return false;
    }
}
