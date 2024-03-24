class Solution {
    static int answer = 0;
    public int solution(int[] numbers, int target) {
        
        
        DFS(0,numbers,target,0);
       
        System.out.println(answer);
        return answer;
    }
    
    public static void DFS(int L, int[] numbers, int target, int sum){
        if(L==numbers.length){
            if(sum==target) answer++;
            return;
        }

        DFS(L+1,numbers,target,sum+numbers[L]);
        DFS(L+1,numbers,target,sum-numbers[L]);
    }
}