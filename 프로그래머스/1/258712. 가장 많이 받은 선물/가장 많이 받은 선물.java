import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        int answer = 0;
        // 서로 간의 더 많이 준 사람이 다음달에 받음
        // 서로 주고 받지 않았거나 주고받은 수가 같으면 선물지수가 작은사람에게 받음 -> 만약 선물지수도 같다면 안주고받음 .
        // 선물지수 = 준 갯수 - 받은 갯수
        int[][] giftTables= new int[50][50]; //주고 받은 기록
        int[] giftRecords = new int[friends.length]; // 선물지수
        int[] receives = new int[friends.length];

        HashMap<String,Integer> index = new HashMap<>();
        HashMap<Integer, String> map = new HashMap<>();
        for (int i=0; i<friends.length; i++){
            index.put(friends[i],i);
            map.put(i, friends[i]);
        }

        for(String gift:gifts){
            String[] tmp = gift.split(" ");
            String giver = tmp[0];
            String receiver = tmp[1];
            // 선물지수
            giftRecords[index.get(giver)]++;
            giftRecords[index.get(receiver)]--;

            //주고받은 기록
            giftTables[index.get(giver)][index.get(receiver)]++;
        }

        for (int i=0; i<friends.length; i++){
            for (int j=0; j<friends.length; j++){
                if(i==j) continue;
                if(giftTables[i][j] < giftTables[j][i]){
                    receives[index.get(map.get(j))]++;
                } else if (giftTables[i][j] > giftTables[j][i]){
                    receives[index.get(map.get(i))]++;
                } else { //주고받지 않았거나 같으면 선물지수확인
                    if(giftRecords[index.get(map.get(i))]< giftRecords[index.get(map.get(j))]){
                        receives[index.get(map.get(j))]++;
                    } else if(giftRecords[index.get(map.get(i))]> giftRecords[index.get(map.get(j))]) {
                        receives[index.get(map.get(i))]++;
                    }
                }
            }
        }
        int max =0;

        for (int i=0; i<receives.length; i++){
            if(receives[i]>max) max = receives[i];
        }


        System.out.println(max);
        return max/2;
    }
}
