class Solution {
    static char[][] map = new char[3][3];
    public int solution(String[] board) {
        int answer = -1;
        int oCount =0;
        int xCount= 0;

        for (int i=0; i<board.length; i++){
            for (int j=0; j<3; j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j]=='O') oCount++;
                else if(map[i][j]=='X')xCount++;
            }
        }

        answer= (func(oCount,xCount));
        return answer;
    }

    public int func(int oCount, int xCount){
        if(oCount==xCount){
            if(isWin('O')) return 0;
        } else if (oCount>xCount){
            if(xCount+1<oCount) return 0;

            //x가 1줄 차면 o가 많은거 불가
            if(isWin('X')) return 0;
        } else {
            return 0;
        }

        return 1;
    }

    public boolean isWin(char c){

        for (int i=0; i<3; i++){
            //가로 검사
            if(map[i][0]==c && map[i][1]==c && map[i][2]==c){
                return true;
            }
            //세로 검사
            if(map[0][i]==c && map[1][i]==c && map[2][i]==c){
                return true;
            }
        }
        if(map[0][0]==c && map[1][1]==c && map[2][2]==c) return true;
        if(map[0][2]==c && map[1][1]==c && map[2][0]==c) return true;

        return false;
    }
}