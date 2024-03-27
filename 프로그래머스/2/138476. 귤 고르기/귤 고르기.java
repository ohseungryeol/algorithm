import java.util.*;
class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer,Integer> map = new HashMap<>();
        
        for (int i=0; i<tangerine.length; i++){
            map.put(tangerine[i],map.getOrDefault(tangerine[i],0)+1);
            
        }
        
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort((o1,o2) -> map.get(o2).compareTo(map.get(o1)));
        int answer = 0;
        
        for (Integer key: keySet){
            if(k<=0) break;
            answer++;
            k-=map.get(key);
        }
        // k개를 골랐을 때 서로 다른 종류가 최소로 
       
        
        
        // 1 [2 2] [3 3] 4 [5 5] 
        return answer;
    }
}