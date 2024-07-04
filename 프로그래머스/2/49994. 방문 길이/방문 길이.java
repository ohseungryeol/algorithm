import java.util.*;

class Solution {
    static int[] dx = {-1,1,0,0}; //u d r l 순 
    static int[] dy = {0,0,1,-1};
    static int answer;
    static HashMap<Character,Integer> dir = new HashMap<>();
    static class Path{
        int sx,sy;
        int ex,ey;
        public Path(int sx,int sy, int ex, int ey){
            this.sx =sx;
            this.sy=sy;
            this.ex=ex;
            this.ey=ey;
        }
    }
    public int solution(String dirs) {
        answer = 0;
        
        dir.put('U',0);
        dir.put('D',1);
        dir.put('R',2);
        dir.put('L',3);
        
        int[][] map = new int[11][11];
        
        process(5,5,map,dirs);
        return answer;
        
        // 경계가 넘어가는 명령은 무시 
        // A: 캐릭터가 처음 걸어본 길의 길이는 ? 
    }
    
    public static void process(int x, int y,int[][] map,String dirs){

        List<Path> paths = new ArrayList<>();
        
        for (int i=0; i<dirs.length(); i++){
            char move = dirs.charAt(i);
           
            int nx = x+dx[dir.get(move)];
            int ny = y+dy[dir.get(move)];
            //범위 밖 명령어 무시 
            if(!isRange(nx,ny)) continue;
            
            if(paths.size()==0){
                answer++;
                paths.add(new Path(x,y,nx,ny));
            } 
            else{
                if(!visited(x,y,nx,ny,paths)){
                    paths.add(new Path(x,y,nx,ny));
                    answer++;
                }
            }
            x= nx;
            y= ny;
        }
    }
    public static boolean visited(int x, int y, int nx, int ny, List<Path> paths){
        
        for (Path path: paths){
            //방문한 경로이면 
            if( (x==path.sx && y==path.sy && nx==path.ex && ny==path.ey) || (x==path.ex && y==path.ey && nx==path.sx && ny==path.sy) ){
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean isRange(int nx, int ny){
        return (nx>=0 && nx<=10 && ny>=0 && ny<=10);
    }
}