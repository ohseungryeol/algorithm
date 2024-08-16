
import java.util.*;

class Solution {
    static HashMap<Character,Integer> map = new HashMap<>();
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int seq=1;
        for (int i=0; i<skill.length(); i++){
            char c = skill.charAt(i);
            map.put(c,seq++);
        }

        for(String str:skill_trees){
            if(impossibleSkill(str)){
                answer++;
            }

        }

        return answer;
    }

    public static boolean impossibleSkill(String str){
        
    
        // 2는 1이 앞, 3은 2가 앞, 4는 3이 앞 
        int seq=0;
        for(int i=0; i<str.length(); i++){
            char c = str.charAt(i);
            
            if(map.containsKey(c)){
                if(map.get(c)!=seq+1) return false;
                seq=map.get(c);
            }
        }

        return true;
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution("CBD",new String[]{"BACDE", "CBADF", "AECB", "BDA"});
    }
}
