
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int sum;
    static char[][] map;
    static int R,C;
    static boolean[][] check;
    public List<Integer> solution(String[] maps) {
        List<Integer> answer = new ArrayList<>();
        R=maps.length;
        C = maps[0].length();
        map = new char[maps.length][maps[0].length()];
        check = new boolean[maps.length][maps[0].length()];
        int idx=0;
        for (String s:maps){
            for (int j=0; j<s.length(); j++){
                map[idx][j]=s.charAt(j);
            }
            idx++;
        }
        
        boolean flag = false;
        for (int i=0; i<R; i++){
            for (int j=0; j<C; j++){
                if(!check[i][j] && Character.isDigit(map[i][j])){
                    flag = true;
                    check[i][j]=true;
                    sum=0;
                    DFS(i,j);
                    answer.add(sum);
                }
            }
        }
        
        if(!flag){
            answer.add(-1);
        } else {
            Collections.sort(answer);
        }
        return answer;
    }

    public static void DFS(int x, int y){
        sum+=map[x][y]-'0';
        for (int i=0; i<4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];

            if(isRange(nx,ny) && !check[nx][ny] && Character.isDigit(map[nx][ny])){
                check[nx][ny]=true;
                DFS(nx,ny);
            }
        }
    }

    public static boolean isRange(int nx,int ny){
        return (nx>=0 && nx<R && ny>=0 && ny<C);
    }
}
