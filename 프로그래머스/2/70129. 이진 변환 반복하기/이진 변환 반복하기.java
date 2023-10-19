import java.util.*;
class Solution {
    public int[] solution(String s) {
        int[] answer = new int[2];
        int delZero=0; //0이 제거된 횟수
        int count=0; // 이진변환 횟수

        while(!s.equals("1")) {
            char[] tmp = new char[s.length()];
            for (int i = 0; i < tmp.length; i++) tmp[i] = s.charAt(i);
            String str = "";
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] == '1') {
                    str += tmp[i];
                } else {
                    delZero++;
                }
            }
            int len = str.length();
            s = Integer.toBinaryString(len);
            count++;
        }

        answer[0]=count;
        answer[1]=delZero;
        return answer;
    }
}