class Solution {
    public int solution(int n) {
        int answer = 0;
        int f1 = 0;
        int f2 = 1;
        int d = 1234567;
        
        for(int i = 2; i <= n; i++){
            answer = (f1 + f2) % d;
            f1 = f2;
            f2 = answer;
        }
        
        return answer;
    }
}