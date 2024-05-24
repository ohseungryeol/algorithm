import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
            if(this.start==o.start) return this.end-o.end;
            return this.start-o.start;
        }
    }
    public int solution(String[][] book_time) {
        int answer = 0;
        List<Info> infoList = new ArrayList<>();
        for(String[] book: book_time){
            String h = book[0];
            String m = book[1];
            infoList.add(new Info(toMinutes(h), toMinutes(m)));
        }

        Collections.sort(infoList);
        //int prevEndTime = infoList.get(0).end;
        int room =1;
        boolean[] roomReserved = new boolean[infoList.size()];
        for (int i=1; i<infoList.size(); i++){
            int curStartTime = infoList.get(i).start;
            int curEndTime = infoList.get(i).end;
            boolean isRoom = false;
            // 전 예약 리스트들
            for (int j=0; j<i; j++){
                int prevEndTime = infoList.get(j).end;
                //전에 있던 방에 입실 가능한지
                if(!roomReserved[j] && curStartTime>= prevEndTime+10){
                    //가능하면 해당 방에 입실하고 퇴실시간을 늘린다.
                    infoList.get(j).end = curEndTime;
                    // 그리고 현재 예약은 지운다.
                    roomReserved[i]=true;
                    //infoList.remove(infoList.get(i));
                    isRoom = true;
                    break;
                }
            }

            if(!isRoom) room++;
        }

        
        return room;
    }

    
    public static int toMinutes(String time){
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]);
        int minute = Integer.parseInt(times[1]);

        return hour*60+minute;
    }
}
