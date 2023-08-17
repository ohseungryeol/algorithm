import java.util.*;
class Solution {
     static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};

    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        for (int z=0; z<5; z++) answer[z]=1;
        char[][] map;
        for (int i=0; i<places.length; i++){
            boolean personsEmpty = true;
            String[] tmp = places[i];
            map = new char[5][5];
            for (int j=0; j<5; j++){
                for (int k=0; k<5; k++){
                    map[j][k]=tmp[j].charAt(k);
                }
            }

            for (int j=0; j<5; j++){
                for (int k=0; k<5; k++){
                    if(map[j][k]=='P'){
                        personsEmpty=false;
                        if(!OneDistance(j,k,map)){
                            answer[i]=0;
                            break;
                        }
                        if(!linearTwoDistance(j,k,map)){
                            answer[i]=0;
                            break;
                        }
                        if(!diagonalDistance(j,k,map)){
                            answer[i]=0;
                            break;
                        }

                    }
                }
            }
            if(personsEmpty==true) answer[i]=1;
        }

        System.out.println(Arrays.toString(answer));
        return answer;
    }

    public boolean OneDistance(int x,int y, char[][] map){
        for (int i=0; i<4; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(isRange(nx,ny) && map[nx][ny]=='P')  return false;
        }

        return true;
    }

    public boolean diagonalDistance(int x, int y, char[][] map){
        int leftUpX = x-1;
        int leftUpY = y-1;

        if(isRange(leftUpX,leftUpY)){
            if(map[leftUpX][leftUpY]=='P'){
                if(map[x-1][y]!='X' || map[x][y-1]!='X'){
                    return false;
                }
            }
        }

        int rightUpX = x-1;
        int rightUpY = y+1;

        if(isRange(rightUpX,rightUpY)){
            if(map[rightUpX][rightUpY]=='P'){
                if(map[x-1][y]!='X' && map[x][y+1]!='X') {
                    return false;
                }
            }
        }

        int leftDownX = x+1;
        int leftDownY = y-1;

        if(isRange(leftDownX,leftDownY)){
            if(map[leftDownX][leftDownY]=='P'){
                if(map[x][y-1]!='X' || map[x+1][y]!='X') {
                    return false;
                }
            }
        }

        int rightDownX = x+1;
        int rightDownY = y+1;

        if(isRange(rightDownX,rightDownY)){
            if(map[rightDownX][rightDownY]=='P'){
                if(map[x][y+1]!='X'|| map[x+1][y]!='X'){
                    return false;
                }
            }
        }

        return true;
    }

    public boolean linearTwoDistance(int x, int y, char[][]map){
        int upX = x-2;
        int upY = y;

        if(isRange(upX,upY)){
            if(map[upX][upY]=='P'){
                if(map[x-1][y]!='X') return false;
            }
        }

        int rightX = x;
        int rightY = y+2;

        if (isRange(rightX,rightY)){
            if(map[rightX][rightY]=='P'){
                if(map[x][y+1]!='X') return false;
            }
        }
        int downX = x+2;
        int downY = y;

        if(isRange(downX,downY)){
            if(map[downX][downY]=='P'){
                if(map[x+1][y]!='X') return false;
            }
        }

        int leftX = x;
        int leftY = y-2;

        if(isRange(leftX,leftY)){
            if(map[leftX][leftY]=='P'){
                if(map[x][y-1]!='X') return false;
            }
        }

        return true;
    }
     public boolean isRange(int x, int y){
        if(x>=0 && x<5 && y>=0 && y<5) return true;
        return false;
    }

}