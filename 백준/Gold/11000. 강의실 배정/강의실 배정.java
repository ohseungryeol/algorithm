import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Info implements Comparable<Info>{
        int start,end;

        public Info(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Info o) {
            if (this.start==o.start) return this.end-o.end;
            return this.start-o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Info> arr = new ArrayList<>();
        StringTokenizer st;
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr.add(new Info(s, e));
        }

        Collections.sort(arr);

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(arr.get(0).end);
        for (int i=1; i<arr.size(); i++){
            Info cur = arr.get(i);

            // 회의가 끝나기 전에 강의
            if(pq.peek()>cur.start){
                pq.offer(cur.end);
            } else { //같은 강의실 사용 가능
                pq.poll();
                pq.offer(cur.end);
            }
        }

        System.out.println(pq.size());
    }
}
