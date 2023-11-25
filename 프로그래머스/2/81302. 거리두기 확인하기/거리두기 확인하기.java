class Solution {
    static int[] dx ={0,0,0,0,-1,-2,1,2,-1,-1,1,1};
    static int[] dy ={1,2,-1,-2,0,0,0,0,-1,1,-1,1};
    static boolean flag;
    public int[] solution(String[][] places) {
        int[] answer = new int[5];
        
        for (int i=0; i<places.length; i++){
            Character[][] map = new Character[5][5];
            String[] str = places[i];
            
            //맵 입력부 
            for(int j=0; j<5; j++){
                for (int k=0; k<5; k++){
                    map[j][k]=str[j].charAt(k);
                }
            }
            
            flag = true;
            for (int j=0; j<5; j++){
                for (int k=0; k<5; k++){
                    if(map[j][k]=='P'){
                        if(!isDistance(j,k,map)) flag=false;
                    }
                }
            }
            if(flag==true) answer[i]=1;
            else answer[i]=0;
        }
        return answer;
    }
    
    public boolean isDistance(int x,int y,Character[][] map){ //현재 p의 좌표 
        
        //맨하탄거리 이내의 p들을 찾는다. 그러고 규칙 부합하는지 본다.
        for (int i=0; i<12; i++){
            int nx = x+dx[i];
            int ny = y+dy[i];
            if(isRange(nx,ny) && map[nx][ny]=='P'){
                int manhatten = Math.abs(x-nx)+Math.abs(y-ny);
                if(manhatten==1){
                    return false;
                } else if(manhatten==2){
                    if(x==nx){
                        int betweenY = (y+ny)/2;
                        if(map[x][betweenY] !='X') return false;
                    } else if (y==ny){
                        int betweenX = (x+nx)/2;
                        if(map[betweenX][y]!='X') return false;
                    } else if (x!=nx && y!=ny){
                        if(map[x][ny]!='X' || map[nx][y]!='X') return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean isRange(int nx,int ny){
        if(nx>=0 &&nx<5 && ny>=0 && ny<5) return true;
        return false;
    }
}