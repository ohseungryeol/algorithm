class Solution {
     static int answer =0;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        DFS(0,dungeons, k);
        System.out.println(answer);
        return answer;
    }

    public void DFS(int count,int[][] dungeons, int k){


        answer = Math.max(answer, count);

        for (int i=0; i< dungeons.length; i++){
            int minPirodo = dungeons[i][0];
            int finPirodo = dungeons[i][1];

            if(!visited[i] && minPirodo<=k){
                visited[i]=true;
                DFS( count+1, dungeons, k-finPirodo);
                visited[i]=false;
            }
        }
    }
}