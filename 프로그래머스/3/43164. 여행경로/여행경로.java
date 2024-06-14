import java.util.*;
class Solution {
    static List<String> paths = new ArrayList<>();
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        boolean[] usedTickets = new boolean[tickets.length];
        
        DFS("ICN","",usedTickets,tickets);
        
        
        Collections.sort(paths);
        for (String path: paths){
            answer = path.split(" ");
            break;
        }
        return answer;
    }
    
    
    public static void DFS(String cur,String path,boolean[] usedTickets, String[][] tickets){
        path+=cur+" ";
        if(allUsedTicket(usedTickets)){
            paths.add(path);
            return;
        }
        
        for (int i=0; i<tickets.length; i++){
            String start = tickets[i][0];
            String end = tickets[i][1];
            
            if(!usedTickets[i] && cur.equals(start)){
                usedTickets[i]=true;
                DFS(end,path,usedTickets,tickets);
                usedTickets[i]=false;
            } 
        }
        
           
    }
    
    public static boolean allUsedTicket(boolean[] usedTickets){
        
        for (int i=0; i<usedTickets.length; i++){
            if(!usedTickets[i]) return false;
        }
        
        return true;
    }
}