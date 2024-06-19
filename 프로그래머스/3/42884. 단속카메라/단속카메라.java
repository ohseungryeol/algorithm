
import java.util.*;
public class Solution {
    static class Info implements Comparable<Info>{
        int start;
        int end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }


        @Override
        public int compareTo(Info o) {
            return this.end-o.end;
        }
    }
    public int solution(int[][] routes) {
        int answer = 0;
        List<Info> cars = new ArrayList<>();
        for (int[] route:routes){
            cars.add(new Info(route[0], route[1]));
        }

        Collections.sort(cars);
        answer++; // 가장 작은 지점은 무조건 카메라 1개가 필요.
        int prevEnd = cars.get(0).end;
        for (int i=1; i<cars.size(); i++){

            //첫번째부터
            Info cur = cars.get(i);
            int start = cur.start;
            int end = cur.end;

            if(prevEnd<start){
                answer++;
                prevEnd = end;
            }

        }

        System.out.println(answer);
        return answer;
    }

    public static void main(String[] args) {
        Solution main = new Solution();
        main.solution(new int[][]{{-20, -15}, {-14, -5}, {-18, -13}, {-5, -3}});
    }
}
