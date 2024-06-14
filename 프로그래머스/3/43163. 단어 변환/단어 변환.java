import java.util.*;
class Solution {
    static int answer = Integer.MAX_VALUE;
    
    public int solution(String begin, String target, String[] words) {
        
        boolean[] visited = new boolean[words.length];
        DFS(begin,target,0,words,visited);
        if(answer==Integer.MAX_VALUE){
            return 0;
        }
        
        return answer;
    }
    
    public static void DFS(String word,String target, int count,String[] words,boolean[] visited){
        if(word.equals(target)){
            answer = Math.min(answer,count);
            return;
        }
        
        for (int i=0; i<words.length; i++){
            if(!visited[i] && isOneDiff(word,words[i])){
                visited[i]=true;
                DFS(words[i],target,count+1,words,visited);
                visited[i]=false;
            }
        }
        
    }
    
    public static boolean isOneDiff(String str1, String str2){
        int len = str1.length();
        for (int i=0; i<str1.length(); i++){
            if(str1.charAt(i)==str2.charAt(i)) len--;
        }
        
        return (len==1);
    }
}