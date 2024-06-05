import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] map;
    static boolean[] visited;
    static boolean flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while(T-->0){
            int numOfStore = Integer.parseInt(br.readLine());
            visited = new boolean[numOfStore+1];
            map = new int[numOfStore + 2][2];
            StringTokenizer st = new StringTokenizer(br.readLine());
            //상근이 집
            map[0][0] = Integer.parseInt(st.nextToken());
            map[0][1] = Integer.parseInt(st.nextToken());

            //편의점
            for (int i=1; i<numOfStore+1; i++){
                st = new StringTokenizer(br.readLine());
                map[i][0] = Integer.parseInt(st.nextToken());
                map[i][1] = Integer.parseInt(st.nextToken());
            }

            // 목적지
            st = new StringTokenizer(br.readLine());
            map[numOfStore + 1][0] = Integer.parseInt(st.nextToken());
            map[numOfStore + 1][1] = Integer.parseInt(st.nextToken());

            flag = false;
            DFS(0,numOfStore,numOfStore+1);
            if(flag) System.out.println("happy");
            else System.out.println("sad");
        }
    }

    public static void DFS(int L, int n, int end){
        int diff = Math.abs(map[L][0] - map[end][0]) + Math.abs(map[L][1] - map[end][1]);

        if(diff<=1000){
            flag = true;
            return;
        }

        for(int i=1; i<=n; i++){
            if(!visited[i]){
                int storeOfDiff = Math.abs(map[L][0] - map[i][0]) + Math.abs(map[L][1] - map[i][1]);
                if(storeOfDiff<=1000) {
                    visited[i] = true;
                    DFS(i, n, end);
                }
            }
        }
    }
}
