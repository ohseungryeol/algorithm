
import java.util.*;
class Solution {
    static int R;
    static int C;
    static List<boolean[]> uniques = new ArrayList<>();
    static boolean flag;
    public int solution(String[][] relation) {
        //유일성과 최소성을 동시에 보장
        //유일성: 중복없는 유일함.
        //최소성: 굳이 필요하지도 않은 속성을 넣지말자.
        // -> 즉 이름,전공으로도 유일하게 구분 가능한데 여기다가 하나를 왜 더 넣나?
        // 모든 튜플은 유일하게 식별하는 데 꼭 필요한 속성들로만 구성되어야 한다.

        // 후보키의 갯수를 리턴한다.
        int answer = 0;
        R = relation.length;
        C = relation[0].length;

        // 조합


        // [0][0], [1][0],[2][0],[3][0] ....
        // [0][0]+[0][1], [1][0]+[1][1] ....


        for (int i=1; i<=C; i++){
            boolean[] check = new boolean[C];
            flag=true;
            combi(0,0,i,check,relation);
        }


        HashSet<String> uniqueAttributes = new HashSet<>();
        for (boolean[] check: uniques){
            String cols = "";
            for (int i=0; i<check.length; i++){
                if(check[i]){
                    // 123, 12, 1
                    cols+=String.valueOf(i);
                }
            }
            uniqueAttributes.add(cols);
        }


        for (String str: uniqueAttributes){
         //   uniqueAttributes.remove(str);
            boolean[] check = new boolean[str.length()];
            flag = true;
            for (int i=1; i<str.length(); i++){// 0 2 3 4
                combiOfMin(0,0,i,str,check,uniqueAttributes);
            }

            if(flag) answer++;
        }

        System.out.println(answer);
        return answer;


    }

    // str개 중에 모든 4개
    public static void combiOfMin(int L, int start,int num, String str,boolean[] check,HashSet<String> uniqueAttributes){
        //0234에서 모든 조합을 걍 해버리자 . 그럼 23 나오겠지, 있으면 유일성이 아닌거야 ㅇㅋ?
        if(L==num){
            String s = "";
            for (int i=0; i<check.length; i++){
                if(check[i]) s+=str.charAt(i);
            }

            if(!s.equals(str) && uniqueAttributes.contains(s)) flag =false;
            return;
        }
        //str = 0234
        for (int i=start; i<str.length(); i++){
            if(!check[i]){
                check[i]=true;
                combiOfMin(L+1,start+1,num,str,check,uniqueAttributes);
                check[i]=false;
            }
        }

    }

    //최소성 체크함수
    public static boolean isMin(String str, HashSet<String> uniqueAttributes){
        // 예를 들어 [1,2,3] 인데
        // uniques안에 [1,2]있다면 ?
        // [1,2,3] -> [1,2]
        // 즉 부분집합이라면!


        String col = "";
        for (int i=0; i<str.length(); i++){
            col+=str.charAt(i);
            //자신이 아닌데

            if(!col.equals(str) && uniqueAttributes.contains(col)) return false;
        }


        return true;
    }


    // 4C2 -> 순서 상관 X 이름, 학년이나~, 학년, 이름이나 ~
    public static void combi(int L,int start,int num, boolean[] check,String[][] relation){
        //  System.out.println("combi("+L+","+start+")");
        if(L==num){
            if(isUnique(check,relation)){
                boolean[] copy = check.clone();
                uniques.add(copy);
            }
            return;
        }

        for (int i=start; i<C; i++){
            if(!check[i]){
                check[i]=true;
                combi(L+1,start+1,num,check,relation);
                check[i]=false;
            }
        }
    }

    //
    public static boolean isUnique(boolean[] check, String[][] relation ){

        List<Integer> cols = new ArrayList<>();
        for (int i=0; i<check.length; i++){
            if(check[i]) cols.add(i);
        }

        if(cols.size()==0) return false;

        HashSet<String> set = new HashSet<>();

        //문자열을 합치고 중복이 되는가.
        for(int i=0; i<R; i++){
            String str = "";
            for(int col: cols){
                str+=relation[i][col];
            }

            if(set.contains(str)){
                //System.out.println(str);
                return false;
            }

            set.add(str);
        }

        return true;
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution(new String[][]{{"100","ryan","music","2"},
                {"200","apeach","math","2"},
                {"300","tube","computer","3"},
                {"400","con","computer","4"},
                {"500","muzi","music","3"},
                {"600","apeach","music","2"}})
                ;
    }
}