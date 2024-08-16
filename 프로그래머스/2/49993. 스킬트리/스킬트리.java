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
        System.out.println("Str===="+str);
        int last=26;
        for(int i=0; i<str.length(); i++){
                char c =str.charAt(i);
            System.out.println(map.get(c));
                if(map.containsKey(c)){
                    //CBD  123
                    if(map.get(c)<last){
                        return false;
                    } else{
                        last = map.get(c); //2     
                    }
                    
                //BACDE
                //2 13
                }
            }
        
        return true;
    }
}