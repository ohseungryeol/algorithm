import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 큐의 크기
        int M = Integer.parseInt(st.nextToken()); // 뽑을 수 갯수

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> seq = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        LinkedList<Integer> linkedList = new LinkedList<>();

        int last=0;
        for (int i=1; i<=N; i++){ // 1부터 N까지 넣기.
            linkedList.addLast(i);
        }

        st = new StringTokenizer(br.readLine());

        for (int i=0; i<M; i++){
            seq.offer(Integer.parseInt(st.nextToken()));
        }

        int count=0;
        while(true){

            int mid = linkedList.size()/2;
            if(seq.isEmpty()) break;

            // 1 6 3 2 7 9 8 4 10 5
            if(linkedList.getFirst()==seq.peek()){
                linkedList.removeFirst();
                seq.poll();
                continue;
            }

            if(linkedList.indexOf(seq.peek())<=mid){
                linkedList.addLast(linkedList.removeFirst());
                count++;
            } else if (linkedList.indexOf(seq.peek())>mid){
                linkedList.addFirst(linkedList.removeLast());
                count++;
            }
        }

        System.out.println(count);
    }
}
