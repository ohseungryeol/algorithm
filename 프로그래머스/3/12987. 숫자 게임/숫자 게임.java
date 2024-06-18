import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        Arrays.sort(A);
        Arrays.sort(B);
        int idx = 0;
        
        // 1 3 5 7 
        // 2 2 6 8

//         for(int i = 0; i < A.length; i++){
//             if(idx >= A.length) break;

//             while(B[idx] <= A[i]){
//                 idx++;
//                 if(idx >= A.length) break;
//             }

//             if(idx >= A.length) break;
//             idx++;
//             answer++;

//         }
        

        for (int i=0; i<A.length; i++){
            for (int j=idx; j<B.length; j++){
                if(B[j]>A[i]){
                    answer++;
                    idx = j+1;
                    break; 
                }
            }
        }

        return answer;
    }
}