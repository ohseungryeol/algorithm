
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    // 상 우 하 좌  0 != 2 , 1!=3 , 2!=0,  3!=1
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int[][] dist;
    static boolean[][][] visited;
    static int answer;
    static int R;
    static int C;

    static class Point{
        int x,y;
        int dir;
        int cost;

        public Point(int x, int y,  int dir, int cost) {
            this.x = x;
            this.y = y;
            this.dir=dir;
            this.cost=cost;
        }
    }

    public int solution(int[][] board) {
        R = board.length;
        C = board[0].length;
        answer = Integer.MAX_VALUE;
        dist = new int[R][C];
        visited = new boolean[R][C][4];

        BFS(board);

        answer = dist[R-1][C-1];
        System.out.println(dist[R-1][C-1]);
        return answer;

    }

    public static void BFS(int[][] board){
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(0, 0, -1,0));


        for (int[] dis:dist){
            Arrays.fill(dis,Integer.MAX_VALUE);
        }

        dist[0][0]=0;


        while(!queue.isEmpty()){
            Point tmp = queue.poll();
          //  System.out.println(tmp.x+","+tmp.y);
            if(tmp.x==R-1 && tmp.y==C-1){
                continue;
            }

            for (int i=0; i<4; i++){
                int nx = tmp.x+dx[i];
                int ny = tmp.y+dy[i];
                int dir =i;

                if(!isRange(nx,ny) || board[nx][ny]==1) continue;
                if(tmp.dir==-1){
                    queue.offer(new Point(nx, ny, dir,100));
                    visited[nx][ny][dir]=true;
                    dist[nx][ny]=100;
                    continue;
                }
                int w=0;
                if(tmp.dir==dir) w=100;
                else w=600;

                if(!visited[nx][ny][dir]){
                    visited[nx][ny][dir]=true;
                    dist[nx][ny]=Math.min(dist[nx][ny],tmp.cost+w);
                    queue.offer(new Point(nx,ny,dir,tmp.cost+w));
                } else {
                    // 방문을 했어도 더 작은 경로라면?
                    if (dist[nx][ny]>=tmp.cost+w){
                        dist[nx][ny]=tmp.cost+w;
                        queue.offer(new Point(nx,ny,dir,tmp.cost+w));
                    }
                }

            }
        }

    }


    public static boolean isRange(int nx , int ny){
        return (nx >= 0 && nx < R && ny >= 0 && ny < C);
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution(new int[][] {{0, 0, 0, 0, 0},{0, 1, 1, 1, 0},{0, 0, 1, 0, 0},{1, 0, 0, 0, 1},{1, 1, 1, 0, 0}});
    }


}
