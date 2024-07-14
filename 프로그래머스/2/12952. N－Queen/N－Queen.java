class Solution {
    static int[][] board;
    static int answer;
    public int solution(int n) {
        answer = 0;
        board = new int[n][n];
        DFS(0,0,n);
        return answer;
    }
    
    public static void DFS(int row, int count, int n){
        if(row==n){
            if(count==n) answer++;
            return;
        }
        
        for (int col=0; col<n; col++){
            if(isAvailable(row,col,n)){
                board[row][col]=1;
                DFS(row+1,count+1,n);
            } else {
                board[row][col]=0;
            }
        }
    }
    
    public static boolean isAvailable(int curRow, int curCol, int n){
        
        for (int i=0; i<curRow; i++){
            for (int j=0; j<n; j++){
                //퀸의 자리와 나의 좌표가 겹치면. 
                if(board[i][j]==1){
                    // 해당 열이 같거나 기울기 같으면 false
                    if(j==curCol || Math.abs(i-curRow) == Math.abs(j-curCol)) return false;
                }
            }
        }
        
        return true;
    }
    
    
}