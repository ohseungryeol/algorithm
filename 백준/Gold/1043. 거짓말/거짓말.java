import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); //사람 수
        int M = Integer.parseInt(st.nextToken()); //파티 수
        ArrayList<Integer> detectives = new ArrayList<>();

        // 파티 번호, 참여인원 map
        HashMap<Integer, ArrayList<Integer>> party = new HashMap<>();
        int[] parents = new int[N + 1];
        for (int i=1; i<=N; i++) parents[i]=i;

        boolean[] lies = new boolean[M]; //파티별 거짓말 여부
        st = new StringTokenizer(br.readLine());
        int numDetective = Integer.parseInt(st.nextToken());

        for (int i=0; i<numDetective; i++){
            detectives.add(Integer.parseInt(st.nextToken()));
        }

        for (int partyNum=0; partyNum<M; partyNum++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            boolean flag = false;


            ArrayList<Integer> members = new ArrayList<>();
            int first = Integer.parseInt(st.nextToken());
            members.add(first);
            //집합 분리
            for (int j=1; j<num; j++){
                int member = Integer.parseInt(st.nextToken());
                members.add(member);
                unionParent(parents,first,member);
            }

            party.put(partyNum,members);


        }

        int answer =0;
        for (int i=0; i< party.size(); i++){
            ArrayList<Integer> partyOfMembers = party.get(i);
            boolean flag = true;
           for (int j=0; j<detectives.size(); j++){
               for (int k=0; k<partyOfMembers.size(); k++){
                   if(sameParent(parents,detectives.get(j),partyOfMembers.get(k))){
                       flag= false;
                       break;
                   }
               }
           }
            if(flag) answer++;
        }

        System.out.println(answer);

    }

    //부모노드를 찾는 함수
    public static int getParent(int[] parents, int x){
        if(parents[x]==x) return x;
        return parents[x]=getParent(parents,parents[x]);
    }

    //두 부모노드를 합치는 함수
    public static void unionParent(int[] parents, int a, int b){
        a = getParent(parents, a);
        b = getParent(parents, b);

        //부모노드는 번호가 작은 순으로 합침
        if(a<b) parents[b]=a;
        else parents[a]=b;
    }

    //부모가 같은지 (부모가 같으면 연결된 것임)
    public static boolean sameParent(int[] parents, int a, int b){
        a = getParent(parents,a);
        b = getParent(parents,b);
        if(a==b) return true;

        return false;
    }

}
