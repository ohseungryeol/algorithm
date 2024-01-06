import java.util.LinkedList;
import java.util.Queue;
class Solution {
    
  static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static char[][] map;
    static int R,C;
    static Queue<int[]> queue = new LinkedList<>();
    static boolean[][] visited;
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String[] board) {

        map = new char[board.length][board[0].length()];
        visited = new boolean[board.length][board[0].length()];
        R = board.length;;
        C = board[0].length();
        for (int i=0; i< board.length; i++){
            String tmp = board[i];
            for (int j=0; j<tmp.length(); j++){
                map[i][j] = tmp.charAt(j);
                if(map[i][j]=='R'){
                    queue.offer(new int[]{i, j, 0});
                    visited[i][j]=true;
                }
            }
        }

        BFS();
        if(answer ==Integer.MAX_VALUE) return -1;
        else return answer;
        
    }

    public static void BFS(){
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int count = tmp[2];

            if(map[x][y]=='G'){
                answer = Math.min(answer,count);
        
            }
            for (int i=0; i<4; i++){
                int nx = x+dx[i];
                int ny = y+dy[i];

                if(isRange(nx,ny) &&  map[nx][ny]!='D') {
                    while (isRange(nx, ny) && map[nx][ny] != 'D' ) { // 다음 좌표가 D가 아니고 범위 안이면 반복
                        nx += dx[i];
                        ny += dy[i];
                    }

                    nx-=dx[i];
                    ny-=dy[i];
                    if(!visited[nx][ny]) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, count + 1});
                    }
                }
                // 위 반복을 탈출했으면 다음
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        if(nx>=0 && nx<R && ny>=0 && ny<C) return true;
        return false;
    }
}