


import java.util.*;
class Solution {
    static HashMap<String,Integer> map1 = new HashMap<>();
    static HashMap<String,Integer> map2 = new HashMap<>();

    public int solution(String str1, String str2) {
        //알파벳과 숫자를 제외하고 제거
        int answer=0;

        // 대문자로
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();


        int common =0;
        int sum=0;
        // 1. 두글자씩 끊기
        splitTwo(str1,1);
        splitTwo(str2,2);

        
        boolean zeroflag = false;
        if(map1.size()==0 && map2.size()==0) zeroflag= true;
        //자유사도 구하기.

        //교집합 구하기
        for (String key: map1.keySet()){

            //공통이 있다면
            if(map2.containsKey(key)){
                if(map1.get(key)<map2.get(key)){
                    //작은 것으로 다중집합
                    common+=map1.get(key);
                } else {
                    common+=map2.get(key);
                }
            }
        }

        //합집합
        for (String key:map1.keySet()){
            // 다중집합일 때
            if(map2.containsKey(key)){
                //합집합은 큰걸로
                if(map1.get(key)<map2.get(key)){
                    sum+=map2.get(key);
                    map2.put(key, 0);
                    map1.put(key, 0);
                } else {
                    sum+=map1.get(key);
                    map1.put(key,0);
                    map2.put(key, 0);
                }
            }
        }

        for (String key:map1.keySet()){
            sum+=map1.get(key);
        }

        for(String key:map2.keySet()){
            sum+=map2.get(key);
        }




        System.out.println(common);
        System.out.println(sum);
        double usado =0;
        
        if(zeroflag){
            usado=1;
        } else {
            usado = (double)common / sum;
        }
        System.out.println(usado);

        answer = (int)(usado * 65536);

        System.out.println(answer);

        //u double answer = usado * 65536;
        // answer *=65536;

        return answer;
    }

    public static void splitTwo(String str,int type){
        for (int i = 0; i < str.length() - 1; i++) {
            // 두 문자 연결
            String tmp = str.charAt(i) + "" + str.charAt(i + 1);
            if(!tmp.matches("[a-zA-Z]+")) continue;
            if(type==1) map1.put(tmp,map1.getOrDefault(tmp,0)+1);
            else map2.put(tmp,map2.getOrDefault(tmp,0)+1);
        }
    }

}