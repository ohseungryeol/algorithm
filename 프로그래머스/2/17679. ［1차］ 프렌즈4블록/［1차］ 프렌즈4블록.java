
public class Solution {
    static boolean flag_exit;
    public int solution(int m, int n, String[] board) {
        int answer=0;
        char[][] map = new char[m][n];

        int idx=0;
        for (String str:board){
            for (int j=0; j<str.length(); j++){
                map[idx][j]=str.charAt(j);
            }
            idx++;
        }

        while(true) {
            // 4방향 체크

            flag_exit=false;
            boolean[][] check = new boolean[m][n];
            //한번도 안바뀌었으면 종료조건
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    if (map[i][j] == '*') continue;
                    fourCheck(i, j, map[i][j], map, check);
                }
            }

            if(!flag_exit) break;

            // *로 바꿔주기.
            int cnt = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (check[i][j]) {
                        map[i][j] = '*';
                        cnt++;
                    }
                }
            }

            for (int i = 0; i < m-1; i++) {
                for (int j = 0; j < n; j++) {
                    if (map[i][j] != '*' && map[i + 1][j] == '*') {
                        swapChar(i + 1, j, map[i][j], map, m);
                    }
                }
            }

            answer+=cnt;
        }
        System.out.println(answer);
        return answer;
    }
   

    public static void fourCheck(int x, int y, char c,char[][] map,boolean[][] check){
        if(map[x][y]==c && map[x][y+1]==c && map[x+1][y]==c && map[x+1][y+1]==c){
            check[x][y]=true;
            check[x][y+1]=true;
            check[x+1][y]=true;
            check[x+1][y+1]=true;
            flag_exit=true;
        }
    }
    // 1. 4방향 체크하고 *로 바꾸기
    // 2. 한 싸이클이 끝나면 *체크
    public static void swapChar(int x, int y, char c,char[][] map,int m){

        int tmp = x-1;
        int swap=0;
        int bSize =0;
        for (int i=x; i<m; i++){
            if(map[i][y]=='*'){
                bSize++;
                if(i==m-1){
                    swap = i;
                }
            } else {
                swap=i-1;
                break;
            }
        }

        for (int i=swap; i>=bSize; i--){
            map[i][y]=map[tmp--][y];
        }

        for (int i=0;i<bSize; i++){
            map[i][y]='*';
        }
//        map[tmp][y]='*';
//        map[swap][y]=c;

    }
}
