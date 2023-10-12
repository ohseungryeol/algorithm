import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        Integer[] arr = new Integer[people.length];
        boolean[] check = new boolean[arr.length];
        for (int i=0; i<arr.length; i++){
            arr[i]=people[i];
        }
        
    
        Arrays.sort(arr,Collections.reverseOrder());
        int p1=0;
        int p2=people.length-1;
        
        while(p1<=p2){
            if(arr[p1]+arr[p2]<=limit){
                answer++;
                p1++;
                p2--;
            } else {
                answer++;
                p1++;
            }
        }
        
        //최대 2명
        
        return answer;
    }
}