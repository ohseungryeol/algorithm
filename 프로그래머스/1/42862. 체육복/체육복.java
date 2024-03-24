import java.util.*;
class Solution {
    //전체 학생 수 , 잃어버린 사람, 여벌체육복 있는 사람 
    public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        // reverse 학생들은 diff<=1인사람만 빌려줄 수 있다.
        boolean[] have = new boolean[n+1];
        boolean[] reserveCheck = new boolean[n+1];
        HashSet<Integer> r = new HashSet<>();
        for (int i=1; i<=n; i++) have[i]=true;
        for (int i=0; i<lost.length; i++) have[lost[i]] = false;
        for (int i=0; i<reserve.length; i++) r.add(reserve[i]);
        // lost에도 있고 reserve에도 있으면 자신이 입고 빌려줄 수 없음 .
        for (int i=0; i<lost.length; i++){
            int lostPerson = lost[i];
            if(r.contains(lostPerson)){
                 reserveCheck[lostPerson]=true;
                 have[lostPerson] = true;
            }
        }
        Arrays.sort(lost);
        Arrays.sort(reserve);
        //5 7 10
        // 1 4 6 9 
        
        for (int i=0; i<lost.length; i++){
            int lostPerson = lost[i];
            if(have[lostPerson]) continue;
            // reserve:3 
            // lost: 3,4 
            for (int j=0; j<reserve.length; j++){
                int reservePerson = reserve[j];
                if(!reserveCheck[reservePerson] && Math.abs(lostPerson-reservePerson)<=1){
                    reserveCheck[reservePerson]=true;
                    have[lostPerson]=true;
                    break;
                }
            }
        }
        // 1 2 3 4 5 
        // 1,2,3
        
        for (int i=1; i<have.length; i++){
            if(have[i]) answer++;
        }
        
        
        return answer;
    }
}