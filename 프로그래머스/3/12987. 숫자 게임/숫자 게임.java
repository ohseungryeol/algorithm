import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        
        // 각 팀은 대결해서 승리한 쪽이 +1점 
        // A팀의 순서를 보고 자신들의 승점을 가장 높이는 방법은 ? -> 이 때 B팀이 얻는 승점 
        
        //차례로  순서보다 큰걸 집어넣는데 최대한 차이 안나는걸로 집어넣는다.
        Arrays.sort(B);
        Arrays.sort(A);
        List<Integer> arr = new ArrayList<>();
        for (int i=0; i<B.length; i++){
            arr.add(B[i]);
        }
        
        Collections.sort(arr);
        //5 1 3 7
        
        // 28
        for (int i=0; i<A.length; i++){
            int num = A[i];
            
            for (int j=0; j<arr.size(); j++){
                if(arr.get(j)>A[i]){
                    arr.remove(j);
                    answer++;
                    break;
                }
            }
        }
        
        return answer;
    }
}