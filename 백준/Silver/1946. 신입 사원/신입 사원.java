import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info> {
        int first;
        int second;

        public Info(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public int compareTo(Info o) {
            if(this.first==o.first) return this.second-o.second;
            return this.first-o.first;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int t=0; t<T; t++){
            int N = Integer.parseInt(br.readLine());
            List<Info> arr = new ArrayList<>();
            for (int i=0; i<N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int first = Integer.parseInt(st.nextToken());
                int second = Integer.parseInt(st.nextToken());
                arr.add(new Info(first, second));
            }
            Collections.sort(arr);
            // 2개 다 어느 지원자보다 순위가 높다면 탈락.
            int firstMin = arr.get(0).first;
            int secondMin = arr.get(0).second;
            int answer =1;


            for (int i=1; i<arr.size(); i++){
                int first = arr.get(i).first;
                int second = arr.get(i).second;
                // 서류나 면접 중 하나라도 순위가 같거나 높으면 선발, 이미 서류는 정렬
                if(second<=secondMin){
                    answer++;
                }
                if(second<secondMin) secondMin=second;
            }
            System.out.println(answer);
        }
    }
}
