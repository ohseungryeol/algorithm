
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    // 우 하 좌 상 순
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static class Point implements Comparable<Point>{
        int x,y,dir;

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir=dir;
        }

        @Override
        public int compareTo(Point o) {
            if(this.x==o.x) return this.y-o.y;
            return this.x-o.x;
        }
    }

    static class Direction{
        int sec;
        String dir;

        public Direction(int sec, String dir) {
            this.sec = sec;
            this.dir = dir;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N+1][N+1];
        Queue<Direction> dirQueue = new LinkedList<>();



        int K = Integer.parseInt(br.readLine()); // 사과 갯수
        StringTokenizer st;
        // 사과 위치
        for (int i=0; i<K; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y]=1;
        }

        int L = Integer.parseInt(br.readLine()); // 방향 전환 횟수

        // 방향 전환 정보 : C = 왼쪽으로 90도 D = 오른쪽으로 90
        for (int i=0; i<L; i++){
            st = new StringTokenizer(br.readLine());
            int sec = Integer.parseInt(st.nextToken());
            String dir = st.nextToken();
            dirQueue.offer(new Direction(sec, dir));
        }

        boolean[][] visited = new boolean[N + 1][N + 1];
        int sec =0;
        int tailX =1;
        int tailY =1;
        int headX=1;
        int headY=1;

        visited[headX][headY]=true;
        //TODO
        // 1. 사과가 있다면 몸길이를 늘린다. (시작 좌표 끝좌표 저장)
        // 2. 사과가 없으면 몸길이를 1칸 줄임. ( 즉 시작좌표 없앤다.)

        int direction = 0; //우방향으로 초기화.
        int prevDirection=0;
        int lastHeadX=0;
        int lastHeadY=0;

        //꼬리담을 좌표.
        Queue<Point> queue = new LinkedList<>();
        while(true){
            sec++; // 초 증가.

            // 머리 늘리기.
            headX += dx[direction];
            headY += dy[direction];

            // 범위 밖이거나 자신의 몸에 부딪히면 종료.
            if(!isRange(headX,headY) || visited[headX][headY]){
                System.out.println(sec);
                break;
            }

            if(map[headX][headY]==1){ // 사과이면 길이 늘림
                visited[headX][headY]=true;
                map[headX][headY]=0;
            } else { //없으면 꼬리 감소.
                visited[headX][headY]=true;
                visited[tailX][tailY]=false;
                boolean flag =false;

                if(!queue.isEmpty() && queue.peek().x==tailX && queue.peek().y==tailY) {
                    //꼬리가 방향바뀌기 전 head까지 도달했다면 이때부턴 방향 전환.
                    Point poll = queue.poll();
                    tailX += dx[poll.dir];
                    tailY += dy[poll.dir];

                    prevDirection=poll.dir;
                    flag = true;
                }
                if(!flag) {
                    tailX += dx[prevDirection];
                    tailY += dy[prevDirection];
                }
            }

            if(!dirQueue.isEmpty() && dirQueue.peek().sec==sec){
                Direction tmp = dirQueue.poll();
                // 방향전환 로직
                if(tmp.dir.equals("D")){ // 오른쪽으로 90도 회전
                    if(direction==3) direction=0;
                    else direction++;

                } else { //왼쪽으로 90도 회전
                    if(direction==0) direction=3;
                    else direction--;
                }

                // 바뀌기전 head 좌표를 담아둔다.
                queue.offer(new Point(headX,headY,direction));

            }


        }


        // GOAL:게임이 몇초에 끝나는가? (자신과 부딪히거나 다음칸이 벽이면 종료)






    }
    static boolean isRange(int nx, int ny){
        return (nx >= 1 && nx <= N && ny >= 1 && ny <= N);
    }
}
