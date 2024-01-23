class Solution {
     static int route,donut,stick,eight;
  
    public int[] solution(int[][] edges) {

        route=0;
        donut=0;
        stick=0;
        eight=0;

        int[][] lines = new int[1000001][2]; //0: output   1: input
        

        for (int i=0; i<edges.length; i++){
            int start = edges[i][0];
            int end = edges[i][1];

            lines[start][0]++; //out
            lines[end][1]++; //in
            
        }

        int totalOut=0;
        for (int i=1; i<1000001; i++){

            if(lines[i][0]>=2 && lines[i][1]==0){ // 루트
                route = i;
                totalOut=lines[i][0];
            } else if(lines[i][0]>=2 && lines[i][1]>=2){ //8자
                eight++;
            } else if (lines[i][1]>0 && lines[i][0]==0) {  //막대
                stick++;
            }
        }
        
        return new int[]{route,(totalOut-eight-stick),stick,eight};
    }
    
}