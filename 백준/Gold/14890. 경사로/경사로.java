import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int answer=0;
    static int N,L;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken()); //경사로의 길이.
        int[][] map = new int[N][N];
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i=0; i<N; i++){
            if(funRow(i,map)){
                answer++;
            }
            if(funCol(i,map)){
                answer++;
            }
        }

        System.out.println(answer);

    }

    public static boolean funRow (int s, int[][]map){ //0~N
        boolean flag = false; //2중 경사로 탐색용도
        int len=1;
        int prev= map[s][0];
        for (int i=1; i<N; i++){
            int cur = map[s][i];
            int diff = Math.abs(prev-cur);

            if(diff>=2) { //차이가 2이상이면 경사로 x
                return false;
            } else if (diff==1){
                if(cur >prev){ //오르막길
                    if(len<L) return false; //L만큼 길이가 안되면 경사로를 놓을 수 없다.
                    if(flag){ //내리막길이다가 오르막길로 변했을 때
                        if(L*2>len) return false;
                        flag =false;
                    }
                    prev = cur;
                    len=1;
                } else { //내리막길
                    len=1;
                    for (int j=i+1; j<N; j++){
                        if(map[s][j]==cur) len++;
                        else break;
                    }
                    if(len<L) return false;
                    prev = cur;
                    flag = true;
                }

            } else if (diff==0){
                if(!flag) len++;

            }
        }
        return true;
    }

    public static boolean funCol(int s, int[][] map){
        boolean flag = false; //2중 경사로 탐색용도
        int len=1;
        int prev= map[0][s];
        for (int i=1; i<N; i++){
            int cur = map[i][s];
            int diff = Math.abs(prev-cur);

            if(diff>=2) { //차이가 2이상이면 경사로 x
                return false;
            } else if (diff==1){
                if(cur >prev){ //오르막길
                    if(len<L) return false; //L만큼 길이가 안되면 경사로를 놓을 수 없다.
                    if(flag){ //내리막길이다가 오르막길로 변했을 때
                        if(L*2>len) return false;
                        flag =false;
                    }
                    prev = cur;
                    len=1;
                } else { //내리막길
                    len=1;
                    for (int j=i+1; j<N; j++){
                        if(map[j][s]==cur) len++;
                        else break;
                    }
                    //System.out.printf("cur = %d, len = %d\n",cur,len);
                    if(len<L) return false;
                    prev = cur;
                    flag = true;
                }

            } else if (diff==0){
                if(!flag) len++;

            }
        }
        return true;

    }



}
