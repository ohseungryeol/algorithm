
import java.util.Date;
import java.util.HashMap;

public class Solution {
    static boolean[] check;
    static String[] alpha;
    static HashMap<String, Integer> map = new HashMap<>();
    static int count=0;
    public int solution(String word) {
        check = new boolean[5];
        alpha = new String[]{"A", "E", "I", "O", "U"};
        DFS(0,"");
        return map.get(word);
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution("I");

    }

    public static void DFS(int L, String word){
        map.put(word,count++);
        if(L==5){
            return;
        }
        // A E I O U

        for (int i=0; i<5; i++){
            DFS(L+1,word+alpha[i]);
        }

    }
}
