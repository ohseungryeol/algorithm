
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int answer=0;
    static int N;
    static int[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;
    static ArrayList<Point> fishes;
    static Queue<Point> queue = new LinkedList<>();
    static int[][] dis;
    static class Point implements Comparable<Point>{
        int x,y,dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }


        @Override
        public int compareTo(Point o) {
            if(this.dist==o.dist){
                if(this.x==o.x) return this.y-o.y;
                return this.x - o.x;
            }
            return this.dist-o.dist;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                map[i][j]=sc.nextInt();
                if(map[i][j]==9){
                    queue.offer(new Point(i, j, 0));
                    map[i][j]=0;
                }
            }
        }

        BFS();
        System.out.println(answer);
    }
/*
1. 아기상어는 자신의 크기 이하인 곳만 지나갈 수 있음.
2. 먹는거는 자신보다 작은 물고기만
3. 자신의 크기만큼 물고기를 먹을 때마다 크기가 1씩 증가.
goal: 더이상 먹을 물고기가 없을 때 까지 먹는다.

1.먹을 수 있는 물고기가 많을 경우
 -  1-1: 거리순
 -  1-2: 거리가 같을 땐 가장 위에있는 물고기 -> 이것도 많을땐 -> 가장왼쪽
* */
    public static void BFS(){
        int sharkLevel = 2;
        int eatCount =0;
        int c=1;
        while (true) {
            dis = new int[N][N];
            visited = new boolean[N][N];
            fishes = new ArrayList<>();
            while (!queue.isEmpty()) {

                Point tmp = queue.poll();
                visited[tmp.x][tmp.y]=true;
                for (int i = 0; i < 4; i++) {
                    int nx = tmp.x + dx[i];
                    int ny = tmp.y + dy[i];
                    //먹을 수 있는 물고기는 모두 list에 저장
                    if (isRange(nx, ny, sharkLevel) && !visited[nx][ny]) {
                        dis[nx][ny] = dis[tmp.x][tmp.y] + 1;
                        queue.offer(new Point(nx, ny, tmp.dist + 1));
                        if (map[nx][ny] != 0 && map[nx][ny] < sharkLevel) {//먹을 수 있는 물고기인 경우
                            fishes.add(new Point(nx, ny, dis[nx][ny]));
                        }
                        visited[nx][ny]=true;
                    }
                }
            }
            //저장된 물고기 정보에 따른 비교
            if(fishes.size()==0) return;
            if(fishes.size()==1){
                answer+=fishes.get(0).dist;
                fishes.get(0).dist=0;
                queue.offer(fishes.get(0));
                map[fishes.get(0).x][fishes.get(0).y]=0;
                eatCount++;
            } else{ //먹을 수 있는 물고기가 여러개인 경우
                Collections.sort(fishes);
                answer+=fishes.get(0).dist;
                fishes.get(0).dist=0;
                queue.offer(fishes.get(0));
                map[fishes.get(0).x][fishes.get(0).y]=0;
                eatCount++;

            }

            if(eatCount==sharkLevel){
                sharkLevel++;
                eatCount=0;
            }

        }
    }

    public static boolean isRange(int nx, int ny, int SharkLevel){
        if(nx>=0 && nx<N && ny>=0 && ny<N && map[nx][ny]<=SharkLevel){
            return true;
        }

        return false;
    }
}
