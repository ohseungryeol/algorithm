import java.util.*;
class Solution {
    static char[] arr = {'A','C','F','J','M','N','R','T'};
    static boolean[] check= new boolean[8];
    static int answer;
    public int solution(int n, String[] data) {
        answer=0;
        DFS(0,new char[8],data);
        return answer;
    }
    
    public static void DFS(int L,char[] result,String[] data){
        
        if(L==result.length){
            boolean flag =true;
            
            for(String str:data){
                char me = str.charAt(0);
                char you = str.charAt(2);
                char bool = str.charAt(3);
                char diff = str.charAt(4);
                
                if(!func(bool,me,you,diff,result)){
                    flag = false;
                    break;
                }
            }
            if(flag) answer++;
            
            return;
        }
        for (int i=0; i<arr.length; i++){
            if(!check[i]){
                check[i]=true;
                result[L]=arr[i];
                DFS(L+1,result,data);
                check[i]=false;
            }
        }
    }
    
    public static boolean func(char bool, char me, char you, char diff, char[] result){
        int d = Integer.parseInt(String.valueOf(diff));
        int myIdx = 0;
        int youIdx =0;
        for (int i=0; i<result.length; i++){
            if(result[i]==me){
                myIdx=i;
            }
            if(result[i]==you){
                youIdx=i;
            }
        }
        
        if(bool=='='){
            if(Math.abs(myIdx-youIdx)==d+1) return true;
        } else if (bool=='>'){
            if(Math.abs(myIdx-youIdx)>d+1) return true;
        } else {
            if(Math.abs(myIdx-youIdx)<d+1) return true;
        }
        
        return false;
    }
    
    
}