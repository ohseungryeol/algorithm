
import java.util.*;
class Solution {
    static int R;
    static int C;
    public int solution(String[][] relation) {
    
        int answer = 0;
        R = relation.length;
        C = relation[0].length;


        // 조합
        for (int i=0; i<C; i++){
            for (int j=i+1; j<=C; j++){
                if(isUnique(i,j,relation)){
                    answer++;
                    break;
                }
            }
        }

        // [0][0], [1][0],[2][0],[3][0] ....
        // [0][0]+[0][1], [1][0]+[1][1] ....
        //


        return answer;

    }

    //
    public static boolean isUnique(int colStart , int colEnd, String[][] relation ){
        // 유일성 만족하는가.
        HashSet<String> set = new HashSet<>();


        for (int i=0; i<R; i++){
            String str ="";
            for (int j=colStart; j<colEnd; j++){
                str+=relation[i][j];
                // 이미 있는 str이면 유일성 만족
            }

            System.out.println(str);
            if(set.contains(str)){
                return false;
            }

            set.add(str);
        }

        return true;
    }

}