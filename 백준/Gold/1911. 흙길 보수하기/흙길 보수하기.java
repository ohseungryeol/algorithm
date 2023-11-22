import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Puddle implements Comparable<Puddle>{
        int start;
        int end;

        public Puddle(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Puddle o) {
            return this.start-o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int answer=0;
        List<Puddle> arr = new ArrayList<>();
        for (int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr.add(new Puddle(s, e));
        }

        Collections.sort(arr);
        int len = arr.get(0).start;

        for (int i=0; i<arr.size(); i++){
            Puddle puddle = arr.get(i);
            if(arr.get(i).start >len) len = puddle.start;
            while(len<puddle.end){
                len+=L;
                answer++;
            }
        }

        System.out.println(answer);

    }
}
