import java.util.*;
class Solution {
     static boolean[] check;
    static int[] arr;
    static int answer=0;
    public int solution(int[] cards) {

        arr = new int[cards.length + 1];

        for (int i=1; i<= cards.length; i++){
            arr[i]=cards[i-1];
        }

        for (int i=1; i<arr.length; i++){
            int aSize = 0,bSize=0;
            check = new boolean[cards.length+1];
            if(!check[i]){
                ArrayList<Integer> a = new ArrayList<>();
                check[i]=true;
                a.add(i);
                DFS(arr[i],a);
                aSize = a.size();
                // 첫번째 그룹이 끝나고 올 check이면 게임 종료 점수는 0점
                if(isAllCheck()) break;
            }

            for (int j=1; j<arr.length; j++){
                if(!check[j]){
                    ArrayList<Integer> b = new ArrayList<>();
                    check[j]=true;
                    b.add(j);
                    DFS(arr[j],b);
                    bSize = b.size();
                    answer = Math.max(answer,aSize*bSize);
                }
            }
        }
        System.out.println(answer);
        return answer;
    }

    public static void DFS(int nextIndex,ArrayList<Integer> a){

        if(check[nextIndex]){ //이미 열었던 박스이면 종료
            return;
        }
        check[nextIndex]=true;
        a.add(nextIndex);
        DFS(arr[nextIndex],a);

    }

    // 올 check이면 남은 상자가 없는 것
    public static boolean isAllCheck(){
        for (int i=1; i<check.length; i++){
            if(!check[i]) return false;
        }

        return true;
    }
}