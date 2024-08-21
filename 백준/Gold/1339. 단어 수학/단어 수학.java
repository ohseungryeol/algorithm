
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static HashMap<Character, Integer> map = new HashMap<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] alpha = new String[N];

        //0 ~ 9 할당 총 10개의 알파벳
        // ABCDE = A+=10000,B+=1000, C+=100, D+=10, E+=1
        for (int i=0; i<N; i++){
            alpha[i] = br.readLine(); //ABCDE
        }

        for (String str: alpha){
            int len = str.length()-1;
            for (int j=0; j<str.length(); j++){
                char c = str.charAt(j);
                map.put(c, (int) (map.getOrDefault(c,0)+Math.pow(10,len--)));
            }
        }

        //가중치가 높은 순으로 할당
        // map value별 내림차순 정렬
        List<Map.Entry<Character, Integer>> entryList = new LinkedList<>(map.entrySet());
        entryList.sort(new Comparator<Map.Entry<Character, Integer>>() {
            @Override
            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        int num=9;
        for(Map.Entry<Character, Integer> entry : entryList){
            // 높은 숫자로 할당.
            map.put(entry.getKey(), num--);
        }

        //이제 숫자로 만들어서 합.
        int sum=0;

        for (String str:alpha){
            String strNum = "";
            for (int j=0; j<str.length(); j++){
                strNum+=String.valueOf(map.get(str.charAt(j)));
            }
            sum+=Integer.parseInt(strNum);
        }

        System.out.println(sum);
    }

}
