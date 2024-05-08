import java.util.*;

class Solution {
    static HashMap<String, Integer> map = new HashMap<>();
    public List<String> solution(String[] orders, int[] course) {

        for (int i=0; i< course.length; i++){
            int len = course[i];
            for (String order:orders){
                char[] c = order.toCharArray();
                Arrays.sort(c);
                String s = "";
               //
                for (int j=0; j<c.length; j++){
                    s += c[j];
                }
                char[] arr = new char[len];
                boolean[] check = new boolean[order.length()];
                Combi(0,0,len,s,arr,check);
            }
        }
        List<String> answer = new ArrayList<>();

        // hashmap 내림차순 정렬
        List<Map.Entry<String, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

//        for (String key: map.keySet()){
//            System.out.println("key:"+key+" value:"+map.get(key));
//        }

        for (int i=0; i<course.length; i++){
            int max =0;
            for(Map.Entry<String, Integer> entry : entryList){
                if(entry.getKey().length()==course[i] && entry.getValue()>=2){
                    max = Math.max(max, entry.getValue());
                    if(entry.getValue()==max) {
                        answer.add(entry.getKey());
                    }
                }
                //System.out.println("key : " + entry.getKey() + ", value : " + entry.getValue());
            }
        }
        for (int i=0; i<answer.size(); i++){
            System.out.println(answer.get(i));
        }
        Collections.sort(answer);

//        코스요리 : 최소 2가지 단품메뉴 and 최소 2명 이상으로부터 주문된 메뉴 조합
//
//        result: course 갯수만큼 문자열로 return
//                - 오름차순 정렬
//                - 메뉴 구성이 여러개면 모두 배열에 담기.
//
//        1. 조합문제: course 원소만큼 조합 만들기
//        2. 조합된 것을 HashMap에 추가
//        3. answer List에 추가

        return answer;
    }

    public static void Combi(int L, int start, int len, String order, char[] arr, boolean[] check){
        if(L==len){
            String str = "";
            for (int i=0; i<arr.length; i++){
                str+=arr[i];
            }
       //     System.out.println(str);
            map.put(str, map.getOrDefault(str, 0) + 1);
            return;
        }

        for (int i=start; i<order.length(); i++){
            if(!check[i]){
                check[i]=true;
                arr[L]=order.charAt(i);
                Combi(L+1,i,len,order,arr,check);
                check[i]=false;
            }
        }
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution(new String[]{"XYZ", "XWY", "WXA"}, new int[]{2, 3, 4});
    }
}