
import java.util.*;

public class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int r,c;
    static int[] scoreOfCol;
    static boolean[][] landVisited;
    static HashSet<Integer> set;
    static int TotCnt;
    static boolean[] colCheck;
    public int solution(int[][] land) {
        r = land.length;
        c = land[0].length;
        scoreOfCol = new int[c];
        landVisited = new boolean[r][c];
//


        //
//        0 0 0 1 1 1 0 0
//        0 0 0 0 1 1 0 0
//        1 1 0 0 0 1 1 0
//        1 1 1 0 0 0 0 0
//        1 1 1 0 0 0 1 1


        for (int i=0; i<r; i++){
            for (int j=0; j<c; j++){
                if(land[i][j]==1 && !landVisited[i][j]){
                    // DFS를 돌면서 이어진 열들에 점수를 합산한다.
                    colCheck = new boolean[c];
                    TotCnt=0;
                   
                    BFS(i, j,land);

                    for (int c=0; c<colCheck.length; c++){
                        // 체크가 되었다면 해당 열들에 석유 크기 추가
                        if(colCheck[c]) scoreOfCol[c]+=TotCnt;
                    }
                }
            }
        }

        
        int answer =0;
        for (int i=0; i<scoreOfCol.length; i++){
            if(answer<scoreOfCol[i]) answer =scoreOfCol[i];
        }


        return answer;


    }

    public static void BFS(int x, int y,int[][] land){
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        landVisited[x][y]=true;
        colCheck[y]=true;
        TotCnt++;
        
        while(!queue.isEmpty()){
            int[] tmp = queue.poll();
            

            for (int i=0; i<4; i++){
                int nx = tmp[0] + dx[i];
                int ny = tmp[1] + dy[i];
                if(isRange(nx,ny) && !landVisited[nx][ny] && land[nx][ny]==1){
                    landVisited[nx][ny]=true;
                    colCheck[ny]=true;
                    TotCnt++;
                    queue.offer(new int[]{nx,ny});

                }
            }
        }
    }
  
  public static boolean isRange(int nx, int ny){
        return (nx>=0 && nx<r && ny>=0 && ny<c);
    }

  
}
