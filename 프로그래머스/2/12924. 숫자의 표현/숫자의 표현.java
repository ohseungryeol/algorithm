class Solution {
    public int solution(int n) {
        int answer = 0;
        //1 2 3 4 5 6 7 8 9 10 11 12 13 14 15
        int[] arr = new int[n+1];
        for(int i=1; i<=n; i++) arr[i]=i;
        
        int p=1;
       
        int sum=0;
        
        while(p<=n){
            sum=0;
            int seq=p-1;
            for(int i=p; i<=n; i++){
                sum+=i;
                if(i!=seq+1 || sum>n){
                    p++;
                    break;
                }
                
                if(sum==n){
                    answer++;
                    p++;
                    break;
                }
                seq++;
                
            }
        }
        return answer;
    }
}