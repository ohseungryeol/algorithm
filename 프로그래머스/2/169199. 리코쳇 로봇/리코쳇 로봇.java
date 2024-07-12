import java.util.*;

class Solution {
    static int[] dx = {-1,0,1,0};
    static int[] dy = {0,-1,0,1};
    static int R,C;
    static char[][] map;
    static boolean[][] visited;
    static Queue<int[]> queue = new LinkedList<>();
    
    public int solution(String[] board) {
        int answer = 0;
        R= board.length;
        C = board[0].length();
        map = new char[R][C];
        visited = new boolean[R][C];
        // D가 닿을 때 까지 이동.  
        // 최소 움직임.
        int i=0; 
        for (String str:board){
            for (int j=0; j<C; j++){
                map[i][j]=str.charAt(j);
                if(map[i][j]=='R'){
                    queue.offer(new int[]{i,j,0});
                    visited[i][j]=true;
                } 
            }
            i++;
        }
        
        answer = BFS();
        if(answer==Integer.MAX_VALUE) return -1;
        else return answer;
    }
    
    public static int BFS(){
        int answer = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            int x = tmp[0];
            int y = tmp[1];
            int cnt = tmp[2];
            
            if(map[x][y]=='G'){
                answer = Math.min(answer,cnt);
            } 
            
            for (int i=0; i<4; i++){
                int nx =x+dx[i];
                int ny =y+dy[i];
                if(!isRange(nx,ny) || map[nx][ny]=='D') continue;
                // visited를 공유하는게 문제다.
                
                    int[] points = slideMove(nx,ny,i);
                    int px=points[0];
                    int py=points[1];
                    
                    if(!visited[px][py]){
                        visited[px][py]=true;
                        queue.offer(new int[]{px,py,cnt+1});    
                    }
    
                
            }
        }
        
        return answer;
    }
    
    public static int[] slideMove(int x, int y, int dir){
        while(true){
            
            int nx = x+dx[dir];
            int ny = y+dy[dir];
            
            //다음칸이 범위를 벗어나거나 혹은 D이면 현재 좌표를 리턴한다.
            if(!isRange(nx,ny) || map[nx][ny]=='D'){
                return new int[]{x,y};
            }
           
            x =nx;
            y =ny;
        }
    }
    
    public static boolean isRange(int nx, int ny){
        return (nx>=0 && nx<R && ny>=0 && ny<C);
    }
}