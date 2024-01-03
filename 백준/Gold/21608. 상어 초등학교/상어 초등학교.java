import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static int[][] board;
    static Map<Integer, Set<Integer>> prefer = new HashMap<>(); // key , value
    static int n;
    static PriorityQueue<Seat> queue = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i=0; i<n*n; i++){
            st = new StringTokenizer(br.readLine());
            int stu = Integer.parseInt(st.nextToken());
            prefer.put(stu, new HashSet<>());
            for (int j=0; j<4; j++){
                prefer.get(stu).add(Integer.parseInt(st.nextToken()));
            }

            findSeat(stu);
            queue.clear();
        }
        int answer=0;
        for (int i=0; i<n; i++){
            for (int j=0; j<n; j++){
                int cnt=0;
                for (int k=0; k<4; k++){
                    int nx =i+dx[k];
                    int ny =j+dy[k];
                    if(nx>=0 && nx<n && ny>=0 && ny<n){
                        if(prefer.get(board[i][j]).contains(board[nx][ny])){
                          cnt++;
                        }
                    }
                }
                if(cnt==1){
                    answer+=1;
                } else if (cnt==2){
                    answer+=10;
                } else if (cnt==3){
                    answer+=100;
                } else if (cnt==4){
                    answer+=1000;
                }
            }
        }

        System.out.println(answer);
    }

    public static void findSeat(int student){
        for (int x=0; x<n; x++){
            for (int y=0; y<n; y++){
                if(board[x][y]!=0) continue;
                int likeCnt =0;
                int emptyCnt=0;
                for (int i=0; i<4; i++){
                    int nx = x+dx[i];
                    int ny = y+dy[i];

                    if(nx>=0 && nx<n && ny>=0 && ny<n){
                        if(prefer.get(student).contains(board[nx][ny])){
                            likeCnt++;
                        }
                        if(board[nx][ny]==0){
                            emptyCnt++;
                        }
                    }
                }
          //      System.out.printf("Student = %d이고 좌표는 %d %d %d %d\n",student,x,y,likeCnt,emptyCnt);
                queue.add(new Seat(x, y, likeCnt, emptyCnt));
            }
        }
        Seat seat = queue.poll();
        //System.out.printf("Student가 %d 일때 최종 앉을 좌표는 %d %d 입니다.\n",student,seat.x,seat.y);
        board[seat.x][seat.y]= student;
    }




    static class Seat implements Comparable<Seat>{
        int x,y,likeNum,emptyNum;

        public Seat(int x, int y, int likeNum, int emptyNum) {
            this.x = x;
            this.y = y;
            this.likeNum = likeNum;
            this.emptyNum = emptyNum;
        }

        @Override
        public int compareTo(Seat other) {
            if (likeNum != other.likeNum) return other.likeNum - this.likeNum;
            // 인접 빈칸 수로 비교
            if (emptyNum != other.emptyNum) return other.emptyNum - this.emptyNum;
            // 행으로 비교
            if (x != other.x) return x - other.x;
            // 열로 비교
            return y - other.y;
        }
    }
}
