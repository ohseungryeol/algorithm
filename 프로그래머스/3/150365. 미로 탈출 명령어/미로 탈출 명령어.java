import java.util.*;

class Solution {
    static int R;
    static int C;
    static boolean flag;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String answer = "";
    static int sx,sy;
    static int ex,ey;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        R=n;
        C=m;
        sx=x-1;
        sy=y-1;
        ex=r-1;
        ey=c-1;

        int distance = Math.abs(x-r)+Math.abs((y-c));

        if(k<distance || (k-distance)%2!=0){
            return "impossible";
        }

        flag = false;
        
        DFS(sx,sy,0,k,"");

       
        //System.out.println(routes.get(0));
        return answer;
    }

    public static void DFS(int x,int y,int count,int k,String route){
        if(count==k){
            if(x==ex && y==ey){
                answer = route;
                flag=true;
            }
            return;
        }

        for (int i=0; i<4; i++){ //l r u d
            int nx = x+dx[i];
            int ny = y+dy[i];
            char c;


            if(isRange(nx,ny) && (Math.abs(ex-x)+Math.abs(ey-y)<=k-count+1) && !flag){
                if(i==0){
                    c ='d';
                } else if (i==1){
                    c='l';
                } else if (i==2){
                    c ='r';
                } else {
                    c = 'u';
                }
                DFS(nx,ny,count+1,k,route+c);
            }
        }
    }

    public static boolean isRange(int nx, int ny){
        return (nx>=0 && nx<R && ny>=0 && ny<C);
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution(	3, 4, 2, 3, 3, 1, 5);
    }
}