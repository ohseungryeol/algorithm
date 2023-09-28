import java.util.Arrays;
import java.util.*;
class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        Map<String,Integer> map = new HashMap<>();
        
        for (String tmp: participant){
            map.put(tmp,map.getOrDefault(tmp,0)+1);
        }
        
        for (String compl: completion){
            map.put(compl,map.getOrDefault(compl,0)-1);
        }
        
        for (String tmp: participant){
            if(map.get(tmp)>0){
                answer = tmp;
            }
        }
        return answer;
    }
}