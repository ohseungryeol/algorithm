class Solution {
     static int answer=0;
    static boolean[] visited;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        DFS(0, k, dungeons, 0);
        System.out.println(answer);
        return answer;
    }

    public static void DFS(int L, int k, int[][] dungeons, int num){
       // System.out.printf("DFS(%d %d %d)\n",L,k,num);
        answer = Math.max(answer, num);
        if(L==dungeons.length || k<=0){
            return;
        }

        for (int i=0; i< dungeons.length; i++){
            int[] info = dungeons[i];
            int minPiro = info[0]; //최소 필요도
            int minusPiro = info[1]; //소모 필요도
            if(!visited[i] && k>=minPiro){
                visited[i]=true;
                DFS(L+1,k-minusPiro,dungeons,num+1);
                visited[i]=false;
            }
        }
    }
}