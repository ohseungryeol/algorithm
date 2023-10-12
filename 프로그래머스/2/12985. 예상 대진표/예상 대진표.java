class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 1;     
        
        while(true){
            
            if(Math.abs(a-b)!=1 || a/2 == b/2){
                if(a%2==0){
                    a=a/2;
                } else if (a%2==1){
                    a=a/2+1;
                }

                if(b%2==0){
                    b=b/2;
                } else if (b%2==1){
                    b=b/2+1;
                }
            } else {
                break;
            }
            answer++;
            
        }

        
    

        return answer;
    }
}