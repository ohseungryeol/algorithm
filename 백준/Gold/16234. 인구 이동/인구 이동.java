
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N,L,R;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    static boolean hasUnion;
    static int answer=0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


            while(true) {
                visited = new boolean[N][N];
                hasUnion = false;
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        if(!visited[i][j]){
                            queue.offer(new int[]{i,j});
                            visited[i][j]=true;
                            movePeople(i,j);
                        }
                    }
                }
                if(!hasUnion) break;
                answer++;
            }

        System.out.println(answer);
    }

    public static int getDiff(int n1, int n2){
        return Math.abs(n1-n2);
    }

    public static void movePeople( int i, int j) {
        int sum = map[i][j];
        int unionNum =1;
        ArrayList<int[]> group = new ArrayList<>();
        group.add(new int[]{i,j});
        while(!queue.isEmpty()){
            int[] idxArr = queue.poll();
            int x = idxArr[0];
            int y = idxArr[1];

            for (int k=0; k<4; k++){
                int nx = x+dx[k];
                int ny = y+dy[k];

                if(isRange(nx,ny) && !visited[nx][ny]) {
                    int diff = getDiff(map[x][y], map[nx][ny]);
                    if (L <= diff && diff <= R) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        group.add(new int[]{nx,ny});
                        unionNum++;
                        sum += map[nx][ny];
                        hasUnion=true;
                    }
                }
            }
        }

        if(unionNum>1){
            for(int g=0; g<group.size(); g++){
                int[] arr = group.get(g);
                int x = arr[0];
                int y = arr[1];
              //  System.out.println(x+","+y);

                map[x][y]=sum/unionNum;
            }
            //System.out.printf("  sum=%d num=%d\n",sum,unionNum);

        }

    }


    public static boolean isRange(int nx, int ny){
        if(nx>=0 && nx<N && ny>=0 && ny<N) return true;
        return false;
    }


}
